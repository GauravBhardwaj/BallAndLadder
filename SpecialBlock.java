import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SpecialBlock extends Block implements ISpecialBlockSubject
{
    // Interface methods
    public void attach(ISpecialBlockObserver o){}
    public void setState(String collider){}
    public void notifyObservers(){}
    public String getState(){return null;}
    
    /**
     * Act - do whatever the SpecialBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
