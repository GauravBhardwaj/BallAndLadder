/**
 * Interface for the Subject in the Power/PenaltyBlock<-->Notification Observer Pattern
 */
public interface ISpecialBlockSubject  
{
    void attach(ISpecialBlockObserver o);
    void setState(String collider);
    String getState();
    void notifyObservers();
}
