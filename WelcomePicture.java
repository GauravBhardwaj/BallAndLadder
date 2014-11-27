import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A composite which represents a "Picture" made out of several "Graphics", i.e. leaf.
 */
public class WelcomePicture extends Actor implements IWelcomeComponent
{
    private ArrayList<IWelcomeComponent> components = new ArrayList<IWelcomeComponent>();
    private World world;
    
    public WelcomePicture(World world)
    {
        this.world = world;
    }
    
    public void addChild(IWelcomeComponent c)
    {
        components.add(c);
    }
       
    public void removeChild(IWelcomeComponent c)
    {
        components.remove(c);
    }
    
    public void draw()
    {
        for (IWelcomeComponent c : components) {
            c.draw();
        }
    }
        
    public void translateOut()
    {
        for (IWelcomeComponent c : components) {
            c.translateOut();
        }
    }
    
    public int getSize()
    {
        return components.size();
    }
}
