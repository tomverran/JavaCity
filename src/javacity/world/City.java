package javacity.world;
import javacity.world.data.Zone;
import javacity.world.data.Flag;

/**
 * A city class, encapsulates a map
 * and uses tile queries to provide metrics & convenience methods.
 * @author Tom
 */
public class City 
{    
    private Map map;
    
    /**
     * Construct a city using the given map.
     * @param map 
     */
    public City(Map map)
    {
        this.map = map;
    }
    
    /**
     * Occupy a zone, such that it will contain a building
     * @param zone 
     */
    public void occupy(Zone zone)
    {
        Query q = new Query(this.map);
        Tile t = q.withType(zone).withoutFlag(Flag.OCCUPIED).fetchRandom();
        if (t != null) {
            t.addFlag(Flag.OCCUPIED);
        }
    }
    
    /**
     * Desert an occupied zone.
     * @param zone 
     */
    public void desert(Zone zone)
    {
        Query q = new Query(this.map);
        Tile t = q.withType(zone).withFlag(Flag.OCCUPIED).fetchRandom();
        if (t != null) {
            t.unsetFlag(Flag.OCCUPIED);
        }   
    }
    
    /**
     * Get the population of our city
     * @return int
     */
    public int population()
    {
        return this.populationOf(Zone.RESIDENTIAL);
    }
    
    /**
     * Get the population of a certain zone type
     * @param int type One of the above types.
     * @return int
     */
    public int populationOf(Zone type)
    {
        Query q = new Query(this.map);
        return q.withType(type)
                .withFlag(Flag.OCCUPIED)
                .fetchCount();     
    }
    
    /**
     * Get the number of jobs available
     * @return int
     */
    public int availableJobs()
    {
        Query q = new Query(this.map);
        return q.withType(Zone.INDUSTRIAL)
                .withType(Zone.COMMERCIAL)
                .withoutFlag(Flag.OCCUPIED)
                .fetchCount();  
    }
    
    /**
     * Get the number of jobs occupied
     * @return int
     */
    public int occupiedJobs()
    {
        Query q = new Query(this.map);
        return q.withType(Zone.INDUSTRIAL)
                .withType(Zone.COMMERCIAL)
                .withFlag(Flag.OCCUPIED)
                .fetchCount();  
    }
}
