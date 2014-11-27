import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CPowerBlockHandler implements ICollisionHandler
{
    private ICollisionHandler successor = null;
    
    public void handleRequest(Ball ball, BreakoutWorld1 world) {
        PowerBlock powerBlock = ball.getIntersectingPowerBlock();
        if (powerBlock != null) {
            ball.deflectOffBlocks();
            // For updating the Observer Notification
            powerBlock.setState("power");
            // For updating the Observer Scoreboard
            ball.setState("power");
            world.removeObject(powerBlock);
            world.getPaddle().increaseSize();
            Greenfoot.playSound("removeBlock1.mp3");
            Scoreboard.getInstance().decrementBlocksLeft();
        }
        else if (successor != null) successor.handleRequest(ball, world);
        
    }
    
    public void setSuccessor(ICollisionHandler next) {
        this.successor = next;
    }
}
