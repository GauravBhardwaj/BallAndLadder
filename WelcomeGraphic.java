import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A leaf object of the composite (WelcomePicture)
 */
public class WelcomeGraphic extends Actor implements IWelcomeComponent
{
    private String imageName;
    private int x;
    private int y;
    private boolean animate = false;
    private World world;
    
    public WelcomeGraphic(String imageName, int x, int y, World world)
    {
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        this.world = world;
    }
    
    public void addChild(IWelcomeComponent c) {}
    
    public void removeChild(IWelcomeComponent c) {}
    
    public void draw()
    {
        this.setImage(imageName);
        world.addObject(this, x, y);
    }
    
    public void translateOut()
    {
        animate = true;
    }
    
    public void act()
    {
        if (animate) {
            setLocation(getX(), getY()-4);
        }
        if (getY() == 0) world.removeObject(this);
    }
    
    public int getSize()
    {
        return 0;
    }
}
