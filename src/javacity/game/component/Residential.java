package javacity.game.component;
import javacity.world.Map;
import javacity.world.City;
import javacity.world.Type;

/**
 * Class to handle population growth / decline
 * and populate residential zones accordingly
 * @author Tom
 */
public class Residential extends TileGrowth {
    
    
    public Residential(City c, Map m)
    {
        super(m, c, Type.RESIDENTIAL, Type.OCCUPIED_RESIDENTIAL);
    }
    
    /**
     * Adjust the chances for people moving in
     * based on the number of jobs around
     * @return 
     */
    @Override
    public float getGrowthModifier()
    {
        //find number of residents
        int population = this.city.population();
        
        //find number of jobs available
        int jobspaces = this.city.availableJobs();
        
        //find number of jobs taken, adjust growth accordingly
        int jobs = this.city.occupiedJobs(); 

        if (jobspaces > 0) {
            return 0.2f;
        } else if (jobs < population) {
            return -0.2f;
        }
        return 0f;
    }  
}
