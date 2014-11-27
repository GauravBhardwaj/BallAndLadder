import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SScorer
{
    private IScoreStrategy scorer;
    
    public SScorer(IScoreStrategy scorer){
      this.scorer = scorer;
   }

   public int executeStrategy(){
      return scorer.getScore();
   }
}
