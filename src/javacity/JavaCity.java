package javacity;
import javacity.lib.Component;
import javacity.world.City;
import java.util.ArrayList;
import javacity.game.component.Commercial;
import javacity.game.component.Industrial;
import javacity.game.component.Residential;
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
        Simulation sim = new Simulation();
        
        //handle observer game components
        city.registerTileObserver(new TileCost());

        //construct our GUI, which handles itself
        //constructing animation threads etc.
        Gui gui = new Gui(city);
                
        //handle per-cycle game components
        sim.addComponent(new Residential(city));
        sim.addComponent(new Commercial(city));
        sim.addComponent(new Industrial(city));
        sim.addComponent(gui);

        //run our simulation thread.
        Thread simthread = new Thread(sim);
        simthread.start();
    }
}
