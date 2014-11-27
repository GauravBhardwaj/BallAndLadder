import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Actor to show notes/labels on the game. Set images to this actor for showing the notes.
 * Also implements the ISpecialBLockObserver interface to display the power/penalty status in
 * the notification area below the scoreboard.
 */
public class Notification extends Actor implements ISpecialBlockObserver
{
    private ISpecialBlockSubject subject;
    
    public Notification(ISpecialBlockSubject s) {
        this.subject = s;
    }
    public void update()
    {
        if (subject.getState().equals("power"))
            setImage(new GreenfootImage("Power Activated", 14, Color.BLACK, Color.GREEN));
        if (subject.getState().equals("penalty"))
            setImage(new GreenfootImage("Penalty Activated", 14, Color.WHITE, Color.RED));
    }
    
    /**
     * Act - do whatever the Notification wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
