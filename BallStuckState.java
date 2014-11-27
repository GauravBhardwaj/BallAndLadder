public class BallStuckState implements IBallState
{
    private Ball ball;
    
    public BallStuckState(Ball ball)
    {
        this.ball = ball;
    }
    
    public void startMovement()
    {
        // do nothing in the stuck state
    }
    
    public void ballIsOut()
    {
        // does nothing
    }
}
