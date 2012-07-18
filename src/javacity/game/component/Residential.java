package javacity.game.component;
import javacity.world.City;
import javacity.world.Metrics;

/**
 * Class to handle population growth / decline
 * and populate residential zones accordingly
 * @author Tom
 */
public class Residential extends TileGrowth {
    
    
    public Residential(City c)
    {
        super(c, "zone_r", "occupied_r");
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
        int population = Metrics.population(this.city);
        
        //find number of jobs available
        int jobspaces = Metrics.availableJobs(this.city);
        
        //find number of jobs taken, adjust growth accordingly
        int jobs = Metrics.occupiedJobs(this.city); 

        if (jobspaces > 0) {
            return 0.2f;
        } else if (jobs < population) {
            return -0.2f;
        }
        return 0f;
    }  
}
