import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CPenaltyBlockHandler implements ICollisionHandler
{
    private ICollisionHandler successor = null;
    
    public void handleRequest(Ball ball, BreakoutWorld1 world) {
        PenaltyBlock penaltyBlock = ball.getIntersectingPenaltyBlock();
        if (penaltyBlock != null) {
            ball.deflectOffBlocks();
            // For updating the Observer Notification
            penaltyBlock.setState("penalty");
            // For updating the Observer Scoreboard
            ball.setState("penalty");
            world.removeObject(penaltyBlock);
            world.getPaddle().decreaseSize();
            Greenfoot.playSound("removeBlock1.mp3");
            Scoreboard.getInstance().decrementBlocksLeft();
        }
        else if (successor != null) successor.handleRequest(ball, world);
        
    }
    
    public void setSuccessor(ICollisionHandler next) {
        this.successor = next;
    }
}
