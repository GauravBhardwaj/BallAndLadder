import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start Screen of the Game
 */

public class StartScreen extends KinectWorld
{
    private GreenfootSound backgroundMusic;
    private IWelcomeComponent myComponents;
    private boolean initialized = false;
    
    public StartScreen()
    {    
        // Create a Kinect World of 800x600 by passing a scale factor of 1.25
        super(1.25,true);        
        prepare();
    }
    
    public void act()
    {
        super.act();
        // Adding the start screen graphics
        if (!initialized) {
            initialize();
            initialized = true;
        }
        
        // Keyboard controls
        if (Greenfoot.isKeyDown ("up")) myComponents.translateOut();
        if (Greenfoot.isKeyDown ("down")) 
        {
            // show team info
        }
        
        // Kinect Controls
        try {
            if ( this.getTrackedUser()!= null ) kinectController();
        }
        catch (Exception e) {
            //do nothing 
        }
        
        if (getObjects(WelcomeGraphic.class).size() == 0) {
            backgroundMusic.stop();
            Greenfoot.setWorld(new BreakoutWorld1());
        }
    }
    
    public void prepare()
    {
        // Set the World's Background
        setBackground("welcomeBackground.jpg");
    }
    
    /**
     * Adds the start screen composite graphics
     */
    public void initialize()
    {
        myComponents = WelcomeCompositeBuilder.buildStartScreen(this);
        myComponents.draw();
        
        // Adding Background Music
        backgroundMusic = new GreenfootSound("welcomeMusic.mp3");
        backgroundMusic.playLoop();
    }
    
    /**
     * Provides Kinect Controls on the Start Screen
     */
    public void kinectController()
    {
        UserData user = this.getTrackedUser();
        // Starting the game when the user swipes up the right hand above their head
        if (user.getHighestJoint() == Joint.RIGHT_HAND) myComponents.translateOut();
        
        // Showing credits when the user swipes up the left hand above their head
        if (user.getHighestJoint() == Joint.LEFT_HAND) { ;} // show team info
    }
    
}
