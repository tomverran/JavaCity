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
        int growth = 0;
        
        //find number of jobs available
        int jobs = this.getCity().getTilesByType("zone_c").size();
        jobs += this.getCity().getTilesByType("zone_i").size();

        //one family moves in per tick if times are good
        if (jobs > population) {
            growth += 4;
        } else if (jobs < population) {
            growth -= 2;
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
        } else {
            
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
