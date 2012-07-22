package javacity;
import javacity.world.Map;
import javacity.game.model.Commercial;
import javacity.game.model.Industrial;
import javacity.game.model.AbstractModel;
import javacity.game.model.Electricity;
import javacity.game.model.Residential;
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
        Map map = new Map(20,15);
        Simulation sim = new Simulation();
        
        //handle observer game components
        map.registerTileObserver(new TileCost());

        //construct our GUI, which handles itself
        //constructing animation threads etc.
        Gui gui = new Gui(map);
        AbstractModel.setMap(map);
                
        //handle per-cycle game components
        sim.addComponent(new Residential());
        sim.addComponent(new Commercial());
        sim.addComponent(new Industrial());
        sim.addComponent(new Electricity());
        sim.addComponent(gui);

        //run our simulation thread.
        Thread simthread = new Thread(sim);
        simthread.start();
    }
}
