import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Building a composite welcome picture which consists of many graphics.
 */
public class WelcomeCompositeBuilder
{
    public static IWelcomeComponent buildStartScreen(World world)
    {
        WelcomePicture order = new WelcomePicture(world);
        order.addChild(new WelcomeGraphic("welcomeLogo.png", 400, 200, world));
        order.addChild(new WelcomeGraphic("welcomePaddle.png", 400, 560, world));
        order.addChild(new WelcomeGraphic("welcomeBall.png", 400, 535, world));
        //order.addChild(new WelcomeGraphic("background1.png", 400, 1000, world));
        order.addChild(new WelcomeGraphic("welcomeSwipeUp.png", 400, 435, world));
        return order;
    }    
}
