/**
 * Component interface for the Composite Pattern on the Start Screen's elements
 */
public interface IWelcomeComponent  
{
    void addChild(IWelcomeComponent c);
    void removeChild(IWelcomeComponent c);
    void draw();
    void translateOut();
    int getSize();
}
