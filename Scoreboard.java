import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

/**
 * Keeps track of game data such as score, lives, blocks left, notifications. This is also displayed on the screen.
 */
public class Scoreboard extends Actor implements IBallObserver
{   
    // Constants
    private static final int LIVES = 3;
    
    // Singleton scoreboard
    private static Scoreboard scoreboard;
    
    // Singleton data
    private int score = 0;
    private int lives = LIVES;
    private int blocksLeft;
    private Ball myBall;
    private BreakoutWorld1 myWorld;
    
    // For Strategy Pattern
    private int strategyScore;
    private SScorer scorer;
    
    private Scoreboard()
    {
    }
    
    /**
     * The Scoreboard is updated by the Ball when it collides with a block
     */
    public void observerUpdate() {
        scoreboard.myBall = (Ball) getWorld().getObjects(Ball.class).get(0);
        if (myBall.getState().equals("points")) {
            scorer = new SScorer(new SPointsScore());
            strategyScore = scorer.executeStrategy();
        }
        if (myBall.getState().equals("power")) {
            scorer = new SScorer(new SPowerScore());
            strategyScore = scorer.executeStrategy();
        }
        if (myBall.getState().equals("penalty")) {
            scorer = new SScorer(new SPenaltyScore());
            strategyScore = scorer.executeStrategy();
        }
        addScore(strategyScore);
    }
    
    /**
     * Returns an instance of the Singleton object Scoreboard
     * @return The Scoreboard of this world
     */
    public static Scoreboard getInstance(){
        if (scoreboard == null) {
            scoreboard = new Scoreboard();
            scoreboard.setImage(new GreenfootImage(200, 30));
            scoreboard.update();
        }
        return scoreboard;
    }
    
    public void addedToWorld(World world)
    {
        blocksLeft = world.getObjects(Block.class).size() - world.getObjects(ConcreteBlock.class).size();
        myWorld = (BreakoutWorld1) getWorld();
    }
    
    /**
     * Reset the game data on the scoreboard and redraw
     */
    public void resetScoreboard(){
        score = 0;
        lives = LIVES;
        update();
    }
    
    /**
     * Act - do whatever the Lives wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (blocksLeft == 0) myWorld.gameOver();
    }  
    
    /**
     * Add the given amount to the score
     * @param points the number of points to be added to the score
     */
    public void addScore(int points)
    {
        score = score + points;
        update();
    }
    
    /**
     * Decrement blocksLeft by 1
     */
    public void decrementBlocksLeft()
    {
        blocksLeft--;
    }
    
    /**
     * Update the lives as soon as you hit the bottom and take appropriate next actions as per lives left:
     * 1. If lives are left, then update scoreboard
     * 2. If no lives are left, then reset scoreboard and notify World that game is over
     */
    public void updateLives()
    {
        if(lives>1){
            lives--;
            update();
        }
        else{
            resetScoreboard();
            myWorld.gameOver();
        }
    }
    
    /**
     * Update(Redraw) the scoreboard with the latest game data
     */
    public void update()
    {   
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, 200, 80);
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(Font.BOLD));
        img.setFont(img.getFont().deriveFont(16.0F));
        img.drawString("Score: " + score + " Lives: " + (lives), 4, 20);
       
    }
}