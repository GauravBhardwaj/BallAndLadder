import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Paddle extends Actor
{
    private BreakoutWorld1 world;
    
    private Ball myBall;  // used before ball gets released
    private int[] xValues = new int[10];  //creates an array of ten int values all initialized to zero.
    private int counter, index, gap, offset = 0;    //counter increments everytime you enter the act() method.
    private int near_avg, far_avg;
    private Joint rightHand;

    public Paddle()
    {    
    }
    
    /**
     * When the paddle gets created, create a ball as well.
     */
    public void addedToWorld(World world)
    {
        newBall();
        world  = (BreakoutWorld1) getWorld();
    }
    
    public void newBall()
    {
        myBall = new Ball();
        getWorld().addObject (myBall, getX(), getY()-18);
    }
    
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    { 
        // Keyboard Controls
        if (Greenfoot.isKeyDown ("left")) moveSideways(-6);
        if (Greenfoot.isKeyDown ("right")) moveSideways(6);
        if (haveBall() && Greenfoot.isKeyDown ("space")) releaseBall();
        
        // Kinect Controls
        try {
            if ( world.getTrackedUser()!= null ) kinectController();
        }
        catch (Exception e) {
            //do nothing 
        }
    }
    
    /**
     * Provides paddle movement through Kinect
     */
    private void kinectController()
    {
        UserData user = world.getTrackedUser();
        
        // Releasing ball when left hand is raised above the head
        if (haveBall() && user.getHighestJoint() == Joint.LEFT_HAND)
            releaseBall();
          
        // Paddle Movement
        rightHand = user.getJoint(Joint.RIGHT_HAND);
        index = counter % 10;
        xValues[index] = rightHand.getX();
        near_avg = far_avg = 0;
        offset = 0;
        // Averaging x-coords to see which way the hand is moving
        for (int j = 0 ;  j <= 4 ; j++){
            near_avg += xValues[ ( (index - j) + 10) % 10 ];            
        }
        near_avg = near_avg / 5;
        index = index - 5;
        for (int j = 0 ;  j <= 4 ; j++){
            far_avg += xValues[ ( (index - j) + 10) % 10 ];            
        }
        far_avg = far_avg / 5;
        gap = Math.abs(near_avg - far_avg);  //This value indicates how fast the hand is moving.
        //Increasing paddle speed based on hand speed          
        if ( gap > 20){
            offset = ( gap - 20 ) / 5;
        }
        if ( gap > 9  ) {
            if ( near_avg > far_avg )
                moveSideways(7 + offset);
            if ( near_avg < far_avg )
                moveSideways( -1 *( 7 + offset) );
        }
        counter++;
        if (counter == 10000) counter = 0; // Resetting to prevent overflow
    }
    
    /**
     * Move the paddle sideways.
     * When the ball is stuck to the paddle, this also calls on the ball to move along with the paddle.
     */
    private void moveSideways(int dist)
    {
        setLocation (getX() + dist, getY());
        if (myBall != null) {
            myBall.move (dist);
        }
    }
        
    /**
     * Returns true if the ball is stuck to the paddle
     */
    private boolean haveBall()
    {
        return myBall != null;
    }
    
    /**
     * Release the ball associated with the paddle
     */
    private void releaseBall()
    {
        myBall.release();
        myBall = null;
    }
    
    /**
     * Doubles paddle size
     */
    public void increaseSize(){
        GreenfootImage image = this.getImage();
        setImage("paddle1_double.png");
    }
    
    /**
     * Shrinks paddle size to half
     */
    public void decreaseSize(){
        GreenfootImage image = this.getImage();
        setImage("paddle1_half.png");
    }
    
    /**
     * Resets paddle size to normal
     */
    public void resetSize(){
        GreenfootImage image = this.getImage();
        setImage("paddle1_normal.png");
    }
        
}
