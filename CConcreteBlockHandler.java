import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class CConcreteBlockHandler implements ICollisionHandler
{
    private ICollisionHandler successor = null;
    
    public void handleRequest(Ball ball, BreakoutWorld1 world) {
        ConcreteBlock concreteBlock = ball.getIntersectingConcreteBlock();
        if (concreteBlock != null) {
            // Special method for concrete block to deflect off its sides properly
            ball.deflectOffBlocks(concreteBlock);
            Greenfoot.playSound("concrete1.mp3");
        }
        // Since this is the final handler in the chain, request falls off, i.e. remains unprocessed
        // in the chain. This means there is no collision between the ball and any type of block.
        else if (successor != null) successor.handleRequest(ball, world);
        
    }
    
    public void setSuccessor(ICollisionHandler next) {
        this.successor = next;
    }
}

