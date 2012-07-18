package javacity;
import javacity.lib.Component;
import javacity.world.City;
import java.util.ArrayList;
import javacity.game.component.Population;
import javacity.game.component.Workplace;
import javacity.game.observer.TileCost;
import javacity.ui.Gui;

/**
 * The main game class?
 * @author Tom
 */
public class JavaCity 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {    
        City city = new City(20,15);
        
        //handle observer game components
        city.registerTileObserver(new TileCost());
        
        //handle per-cycle game components
        ArrayList<Component> components = new ArrayList<Component>();

        components.add(new Population(city));
        components.add(new Workplace(city));

        //construct our GUI, which handles itself
        //constructing animation threads etc.
        Gui gui = new Gui(city);
        
        //The Loop
        while (true) {
            if (System.currentTimeMillis() % 10000 == 0) {
                for (Component component : components) {
                    component.tick();
                }                
            }
        }
    }
}
