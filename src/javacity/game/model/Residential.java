package javacity.game.model;
import javacity.world.Tile;

/**
 * Class to handle population growth / decline
 * and populate residential zones accordingly
 * @author Tom
 */
public class Residential extends TileGrowth {
    
    
    public Residential()
    {
        super(Tile.Zone.RESIDENTIAL);
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
        int population = this.population();
        
        //find number of jobs available
        int jobspaces = this.availableJobs();
        
        //find number of jobs taken, adjust growth accordingly
        int jobs = this.occupiedJobs(); 

        if (jobspaces > 0) {
            return 0.2f;
        } else if (jobs < population) {
            return -0.2f;
        }
        return 0f;
    }  
}
