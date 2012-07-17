package javacity.game.component;
import javacity.lib.Component;
import javacity.world.City;
import java.util.ArrayList;
import javacity.world.Tile;
/**
 * Class to handle population growth / decline
 * and populate residential zones accordingly
 * @author Tom
 */
public class Population extends Component {
    
    public Population(City c)
    {
        super(c);
    }
    
    @Override
    public void tick()
    {
        //find number of hospitals + number of residents
        int population = this.getCity().getTilesByType("occupied_r").size();
        int housingspaces = this.getCity().getTilesByType("zone_r").size();
        int growth = 0;
        
        //find number of jobs available
        int jobspaces = this.getCity().getTilesByType("zone_c").size();
        jobspaces += this.getCity().getTilesByType("zone_i").size();
        
        //find number of jobs taken
        int jobs = this.getCity().getTilesByType("occupied_c").size();
        jobs += this.getCity().getTilesByType("occupied_i").size();        

        //one family moves in per tick if times are good
        if (jobspaces > 0) {
            growth = Math.min(jobspaces, housingspaces);
        } else if (jobs < population) {
            growth = Math.max(jobs-population,-2);
            System.out.println(jobs + "-" + population);
        }
        
        if (growth > 0) {
            
            //occupy some free residential zones if we have a populace
            ArrayList<Tile> freeZones = this.getCity().getTilesByType("zone_r");
            int countFreeZones = freeZones.size();
            int i = 0;
            
            while (growth > 0 && countFreeZones > 0) {
                freeZones.get(i).setType("occupied_r");
                countFreeZones--;
                growth--;
                i++;
            }            
        } else if (growth < 0) {
            
            //desert some residential zones if our population is shrinking
            ArrayList<Tile> houses = this.getCity().getTilesByType("occupied_r");
            int countHouses = houses.size();
            int i = 0;
            
            while (growth < 0 && countHouses > 0) {
                houses.get(i).setType("zone_r");
                countHouses--;
                growth++;
                i++;
            }                 
        }
    }  
}
