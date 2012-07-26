package javacity;
import javacity.world.Map;
import javacity.game.component.Commercial;
import javacity.game.component.Industrial;
import javacity.game.component.Residential;
import javacity.game.component.Power;
import javacity.game.component.Time;
import javacity.ui.SwingInterface;
import javacity.world.BuildingRepository;
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
        BuildingRepository repo = new BuildingRepository();
        City city = new City(map, repo);


        //construct our GUI, which handles itself
        //constructing animation threads etc.
        SwingInterface gui = new SwingInterface(city, map, SwingInterface.Mode.ISOMETRIC);
                
        //handle per-cycle game components
        sim.addComponent(new Residential(city));
        sim.addComponent(new Commercial(city));
        sim.addComponent(new Industrial(city));
        sim.addComponent(new Power(city));
        sim.addComponent(new Time(city, gui));
        sim.addComponent(gui);

        //run our simulation thread.
        Thread simthread = new Thread(sim);
        simthread.start();
    }
}
