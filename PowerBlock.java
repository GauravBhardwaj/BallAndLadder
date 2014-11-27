import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class PowerBlock extends SpecialBlock implements ISpecialBlockSubject
{
    private ArrayList<ISpecialBlockObserver> observers = new ArrayList<ISpecialBlockObserver>();
    private String collider;
    
    public void attach(ISpecialBlockObserver o) {
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
        for (ISpecialBlockObserver o:observers) {
            o.update();
        }
    }
    
    public PowerBlock(){
    }
    /**
     * Act - do whatever the PowerBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }       
}
    
