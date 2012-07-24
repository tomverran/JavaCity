package javacity;
import javacity.lib.Component;
import javacity.world.Map;
import java.util.ArrayList;
import javacity.game.component.Commercial;
import javacity.game.component.Industrial;
import javacity.game.component.Residential;
import javacity.game.observer.TileCost;
import javacity.ui.Gui;
import javacity.world.City;

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
        Map map = new Map(20,15);
        Simulation sim = new Simulation();
        City city = new City(map);
        
        //handle observer game components
        map.registerTileObserver(new TileCost());

        //construct our GUI, which handles itself
        //constructing animation threads etc.
        Gui gui = new Gui(map);
                
        //handle per-cycle game components
        sim.addComponent(new Residential(city, map));
        sim.addComponent(new Commercial(city, map));
        sim.addComponent(new Industrial(city, map));
        sim.addComponent(gui);

        //run our simulation thread.
        Thread simthread = new Thread(sim);
        simthread.start();
    }
}
