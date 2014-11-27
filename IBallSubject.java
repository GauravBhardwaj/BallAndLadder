/**
 * Interface for the subject in the Ball<-->Scoreboard Observer Pattern
 */
public interface IBallSubject  
{
    void attach(IBallObserver o);
    void setState(String collider);
    String getState();
    void notifyObservers();
}
