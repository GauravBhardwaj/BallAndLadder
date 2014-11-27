import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class BreakoutWorld1 extends KinectWorld
{
    private Paddle paddle;
    private GreenfootSound backgroundMusic;
    
    /**
     * Constructor for objects of class BreakOutWorld1.
     * 
     */
    public BreakoutWorld1()
    {
        // Create a Kinect World of 800x600 by passing a scale factor of 1.25
        super(1.25,true);        
        prepare();
        Greenfoot.playSound("worldLoad1.mp3");
        // Adding Background Music
        backgroundMusic = new GreenfootSound("backgroundMusic1.mp3");
        backgroundMusic.playLoop();   
    }
    
    /**
     * The World's act method. Calls KinectWorld's act method to initialise.
     */
    public void act()
    {
        super.act();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        // Set the World's Background
        setBackground("background1.png");
        
        // Adding Paddle
        paddle = new Paddle();
        paddle.setImage("paddle1_normal.png");
        addObject ( paddle, getWidth() / 2, getHeight() - 40);

        // Adding PointsBlock
        PointsBlock pointsblock = new PointsBlock();
        addObject(pointsblock, 236, 103);
        PointsBlock pointsblock2 = new PointsBlock();
        addObject(pointsblock2, 236, 145);
        PointsBlock pointsblock3 = new PointsBlock();
        addObject(pointsblock3, 130, 103);
        PointsBlock pointsblock4 = new PointsBlock();
        addObject(pointsblock4, 130, 144);
        PointsBlock pointsblock5 = new PointsBlock();
        addObject(pointsblock5, 342, 103);
        PointsBlock pointsblock6 = new PointsBlock();
        addObject(pointsblock6, 452, 103);
        PointsBlock pointsblock7 = new PointsBlock();
        addObject(pointsblock7, 343, 146);
        PointsBlock pointsblock8 = new PointsBlock();
        addObject(pointsblock8, 452, 147);
        PointsBlock pointsblock9 = new PointsBlock();
        addObject(pointsblock9, 560, 102);
        PointsBlock pointsblock10 = new PointsBlock();
        addObject(pointsblock10, 562, 147);
        PointsBlock pointsblock11 = new PointsBlock();
        addObject(pointsblock11, 669, 101);
        PointsBlock pointsblock12 = new PointsBlock();
        addObject(pointsblock12, 670, 145);

        // Adding ConcreteBlock
        ConcreteBlock concreteblock = new ConcreteBlock();
        addObject(concreteblock, 268, 319);
        ConcreteBlock concreteblock2 = new ConcreteBlock();
        addObject(concreteblock2, 395, 319);
        ConcreteBlock concreteblock3 = new ConcreteBlock();
        addObject(concreteblock3, 524, 318);

        // Adding PowerBlock
        PowerBlock powerblock = new PowerBlock();
        addObject(powerblock, 674, 239);
       
        // Adding PenaltyBlock
        PenaltyBlock penaltyblock = new PenaltyBlock();
        addObject(penaltyblock, 130, 236);
        
        // Adding Scoreboard---> NEED to add scoreboard at the end (i.e. after adding the blocks), 
        // as this ensures that the blocksLeft variable is instantiated properly in the Scoreboards' addedToWorld method
        addObject (Scoreboard.getInstance(), 725, 25);
        
        // Adding Power Notification area and attaching it to the PowerBlock subject
        Notification powerNotification = new Notification(powerblock);
        powerblock.attach(powerNotification);
        powerNotification.setImage(new GreenfootImage("", 14, Color.BLACK, Color.GRAY));
        addObject(powerNotification, 725,50);
        
        // Adding Penalty Notification area and attaching it to the PenaltyBlock subject 
        Notification penaltyNotification = new Notification(penaltyblock);
        penaltyblock.attach(penaltyNotification);
        penaltyNotification.setImage(new GreenfootImage("", 14, Color.BLACK, Color.GRAY));
        addObject(penaltyNotification, 725,65);
    }

    /**
     * Ball has exited from the bottom of the world. Resets the paddle to normal size and associates a new ball with it. 
     */
     public void ballIsOut()
    {
        paddle.newBall();
        paddle.resetSize();
    }
    
    /**
     * Resets the scoreboard and clears this world's objects and moves back to the Start Screen
     */
    public void gameOver() {
        this.removeObjects(this.getObjects(Ball.class));
        this.removeObjects(this.getObjects(Block.class));
        this.removeObjects(this.getObjects(Paddle.class));
        this.removeObjects(this.getObjects(Notification.class));
        Scoreboard.getInstance().resetScoreboard();
        backgroundMusic.stop();
        Greenfoot.setWorld(new StartScreen());
    }
    
    /**
     * Returns the paddle in this world
     */
    public Paddle getPaddle()
    {
        return paddle;
    }
}
