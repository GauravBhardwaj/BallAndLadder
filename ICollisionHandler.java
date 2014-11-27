/**
 * Interface for the Handler in the Ball<-->Block Collision detection via 
 * Chain of Responsibility pattern
 */
public interface ICollisionHandler  
{
    void handleRequest(Ball ball, BreakoutWorld1 world);
    void setSuccessor(ICollisionHandler next);
}
