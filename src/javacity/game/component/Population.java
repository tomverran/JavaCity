package javacity.game.component;
import javacity.lib.Component;
import javacity.world.City;
import java.util.ArrayList;
import java.util.Random;
import javacity.world.Tile;
/**
 * Class to handle population growth / decline
 * and populate residential zones accordingly
 * @author Tom
 */
public class Population extends Component {
    
    private Random r;
    
    public Population(City c)
    {
        super(c);
        r = new Random();
    }
    
    @Override
    public void tick()
    {
        //find number of hospitals + number of residents
        int population = this.getCity().getTilesByType("occupied_r").size();

        //random growth factor to simulate people
        //moving in our out for their own reasons.
        double growth = Math.random() - 0.5;
        
        //find number of jobs available
        int jobspaces = this.getCity().getTilesByType("zone_c").size();
        jobspaces += this.getCity().getTilesByType("zone_i").size();
        
        //find number of jobs taken, adjust growth accordingly
        int jobs = this.getCity().getTilesByType("occupied_c").size();
        jobs += this.getCity().getTilesByType("occupied_i").size();        

        if (jobspaces > 0) {
            growth += 0.2;
        } else if (jobs < population) {
            growth -= 0.2;
        }
        
        if (growth > 0.4) {
            this.getCity().switchTypeOfRandom("zone_r", "occupied_r");
        } else if (growth < -0.4) {
            this.getCity().switchTypeOfRandom("occupied_r", "zone_r");             
        }
    }  
}
