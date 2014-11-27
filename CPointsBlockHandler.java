import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CPointsBlockHandler implements ICollisionHandler
{
    private ICollisionHandler successor = null;
    
    public void handleRequest(Ball ball, BreakoutWorld1 world) {
        PointsBlock pointsBlock = ball.getIntersectingPointsBlock();
        if (pointsBlock != null) {
            ball.deflectOffBlocks();
            // For updating the Observer Scoreboard
            ball.setState("points");
            world.removeObject(pointsBlock);
            Greenfoot.playSound("removeBlock1.mp3");
            Scoreboard.getInstance().decrementBlocksLeft();
        }
        else if (successor != null) successor.handleRequest(ball, world);
        
    }
    
    public void setSuccessor(ICollisionHandler next) {
        this.successor = next;
    }
}
