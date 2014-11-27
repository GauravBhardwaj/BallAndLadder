import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The ball of the game. It moves and bounces off the walls and the paddle.
 */
public class Ball extends Actor implements IBallSubject
{
    private int deltaX;         // x movement speed
    private int deltaY;         // y movement speed
    
    private BreakoutWorld1 myWorld;
    
    // Constants
    private static final int PADDLE_SCORE = 1;
    
    // For Observer
    private ArrayList<IBallObserver> observers = new ArrayList<IBallObserver>();
    private String collider;
    
    // For chain of responsibility
    ICollisionHandler pointsHandler = new CPointsBlockHandler();
    ICollisionHandler powerHandler = new CPowerBlockHandler();
    ICollisionHandler penaltyHandler = new CPenaltyBlockHandler();
    ICollisionHandler concreteHandler = new CConcreteBlockHandler();
    
    // For State pattern
    IBallState stuckState;
    IBallState releasedState;
    IBallState currentState;
    
    public Ball()
    {
        // Wiring up chain of responsibility for Ball<-->Block collision detection
        pointsHandler.setSuccessor(powerHandler);
        powerHandler.setSuccessor(penaltyHandler);
        penaltyHandler.setSuccessor(concreteHandler);
        
        // Setting up the instance variables for the ball's State
        stuckState = new BallStuckState(this);
        releasedState = new BallReleasedState(this);
        currentState = stuckState; //ball is stuck initially
    }
    
    public void addedToWorld(World world)
    {
        myWorld = (BreakoutWorld1) world;
        attach(Scoreboard.getInstance());
    }
    
    /**
     * Act. Move if we're not stuck.
     */
    public void act() 
    {
        // Initial state is stuck state.
        // When the release method of the ball is called from the paddle, the state of the ball changes to released state.
        currentState.startMovement();
    }
    
    /**
     * Move the ball along with the paddle, i.e. this method is only used when the ball has not yet been released from the paddle.
     */
    public void move(int dist)
    {
        setLocation (getX() + dist, getY());
    }
    
    /**
     * Checking block collision via chain of responsibility
     */
    public void checkBlockCollision()
    {
        pointsHandler.handleRequest(this, (BreakoutWorld1)this.getWorld());
    }
    
    /**
     * Checks whether the ball has hit one of the three walls. Reverse X/Y direction, depending on which wall the ball hit.
     */
    public void checkWalls()
    {
        if (getX() == 0 || getX() == getWorld().getWidth()-1) {
            deltaX = -deltaX;
        }
        if (getY() == 0) {
            deltaY = -deltaY;
        }
    }
    
    /**
     * Checks if the ball has hit the paddle
     */
    public void checkPaddle()
    {
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if (paddle != null) {
            Greenfoot.playSound("releaseBall1.mp3");
            deltaY = -deltaY;
            int offset = getX() - paddle.getX();
            deltaX = deltaX + (offset/10);
            if (deltaX > 7) {
                deltaX = 7;
            }
            if (deltaX < -7) {
                deltaX = -7;
            }
            Scoreboard.getInstance().addScore(PADDLE_SCORE);
        }            
    }
    
    
    /**
     * Checks whether we're out (bottom of screen).
     */
    public void checkOut()
    {
        if (getY() == getWorld().getHeight()-1) {
            currentState.ballIsOut();
            getWorld().removeObject(this);
            Greenfoot.playSound("die1.mp3");
            Scoreboard.getInstance().updateLives();
        }
    }
        
    /**
     * Release the ball from the paddle.
     */
    public void release()
    {
        Greenfoot.playSound("releaseBall1.mp3");
        deltaX = Greenfoot.getRandomNumber(5) - 2;
        deltaY = -4;
        // Changing ball's state to released state
        currentState = releasedState;

    }
    
    /**
     * Deflects ball when it collides with a block
     */
    public void deflectOffBlocks(){
        deltaY = -deltaY;
    }
    
    /**
     * Deflects ball when it collides with a concrete block.
     * Adds logic to deflect properly off the side of the concrete block.
     */
    public void deflectOffBlocks(Block block){
        int ballX = this.getX();
        
        int ballXLeft = ballX - (this.getImage().getWidth()/2);
        int ballXRight = ballX + (this.getImage().getWidth()/2);
        
        int ballLeftTop = ballXLeft - (this.getImage().getHeight()/2);
        int ballLeftBottom = ballXLeft + (this.getImage().getHeight()/2);
        int ballRightTop = ballXRight - (this.getImage().getHeight()/2);
        int ballRightBottom = ballXRight + (this.getImage().getHeight()/2);
        
        int blockXLeft = block.getX()-(block.getImage().getWidth()/2);
        int blockXRight = block.getX()+(block.getImage().getWidth()/2);
        
        int blockLeftTop = blockXLeft - (block.getImage().getHeight()/2);
        int blockLeftBottom = blockXLeft + (block.getImage().getHeight()/2);
        int blockRightTop = blockXRight - (block.getImage().getHeight()/2);
        int blockRightBottom = blockXRight + (block.getImage().getHeight()/2);
        
        //if((ballXLeft>blockLeftTop && ballXLeft<blockLeftBottom) || (ballXLeft>blockRightTop && ballXLeft<blockRightBottom)){
        //if((ballX>blockLeftTop && ballX<blockLeftBottom) || (ballX>blockRightTop && ballX<blockRightBottom)){
        if((ballRightTop>blockLeftTop && ballRightTop<blockLeftBottom) || (ballLeftTop>blockRightTop && ballLeftTop<blockRightBottom)){
            deltaX = -deltaX;
        }
        else if((ballRightBottom>blockLeftTop && ballRightBottom<blockLeftBottom) || (ballLeftBottom>blockRightTop && ballLeftBottom<blockRightBottom)){
            deltaX = -deltaX;
        }
        else{
            deltaY = -deltaY;
        }
    }
    
    // Methods used in the Ball<-->Scoreboard Observer Pattern
    public void attach(IBallObserver o) {
        observers.add(o);
    }
    
    public void setState(String collider) {
        this.collider = collider;
        notifyObservers();
    }
    
    public String getState() {
        return collider;
    }
    
    public void notifyObservers() {
        for (IBallObserver o : observers) {
            o.observerUpdate();
        }
    }
    
    // Methods used when using chain of responsibility to detect Ball<-->Block collision
    // Required to use getOneIntersectingObject method here, as it is protected and cannot be used from within the handlers in the chain.
    public PointsBlock getIntersectingPointsBlock()
    {
        return (PointsBlock) this.getOneIntersectingObject(PointsBlock.class);
    }

    public PowerBlock getIntersectingPowerBlock()
    {
        return (PowerBlock) this.getOneIntersectingObject(PowerBlock.class);
    }
    
    public PenaltyBlock getIntersectingPenaltyBlock()
    {
        return (PenaltyBlock) this.getOneIntersectingObject(PenaltyBlock.class);
    }
    
    public ConcreteBlock getIntersectingConcreteBlock()
    {
        return (ConcreteBlock) this.getOneIntersectingObject(ConcreteBlock.class);
    }
    
    // Getters for deltaX and deltaY
    public int getDeltaX() { return deltaX; }
    public int getDeltaY() { return deltaY; }
}
