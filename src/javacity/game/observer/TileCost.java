package javacity.game.observer;
import java.util.Observer;
import java.util.Observable;
/**
 *
 * @author Tom
 */
public class TileCost implements Observer 
{    
    @Override
    public void update(Observable o, Object args)
    {
        System.out.println("funds --");
    }
}
