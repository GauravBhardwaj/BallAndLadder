public class BallReleasedState implements IBallState
{
    private Ball ball;
    
    public BallReleasedState(Ball ball)
    {
        this.ball = ball;
    }
    
    public void startMovement()
    {
        ball.setLocation(ball.getX()+ball.getDeltaX(), ball.getY()+ball.getDeltaY());
        ball.checkBlockCollision();
        ball.checkWalls();
        ball.checkPaddle();
        ball.checkOut();
    }
    
    public void ballIsOut()
    {
        BreakoutWorld1 world = (BreakoutWorld1) ball.getWorld();
        world.ballIsOut();
        // Not changing ball state, since the ball is now removed from the system and a new
        // ball object is used. The new ball object is in the stuck state by default when its
        // constructor is called
    }
    
}
