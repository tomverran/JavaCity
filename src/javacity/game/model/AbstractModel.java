package javacity.game.model;
import javacity.lib.Component;
import javacity.world.Map;
import javacity.world.Map.Query;
import javacity.world.Tile;

/**
 * A base class for all the objects that model our city.
 * @author Tom
 */
public abstract class AbstractModel implements Component {
    
    protected static Map map;
    public static void setMap(Map m)
    {
        AbstractModel.map = m;
    }
    
    /**
     * Occupy a zone, such that it will contain a building
     * @param zone 
     */
    protected void occupy(Tile.Zone zone)
    {
        Tile t = map.new Query().withType(zone)
                            .withFlag(Tile.Flag.POWERED)
                            .withoutFlag(Tile.Flag.OCCUPIED)
                            .fetchRandom();
        if (t != null) {
            t.addFlag(Tile.Flag.OCCUPIED);
        }
    }
    
    /**
     * Desert an occupied zone.
     * @param zone 
     */
    protected void desert(Tile.Zone zone)
    {
        Tile t = map.new Query().withType(zone)
                            .withFlag(Tile.Flag.OCCUPIED)
                            .fetchRandom();
        if (t != null) {
            t.unsetFlag(Tile.Flag.OCCUPIED);
        }   
    }
    
    /**
     * Get the population of our city
     * @return int
     */
    protected int population()
    {
        return this.populationOf(Tile.Zone.RESIDENTIAL);
    }
    
    /**
     * Get the population of a certain zone type
     * @param int type One of the above types.
     * @return int
     */
    protected int populationOf(Tile.Zone type)
    {
        return map.new Query().withType(type)
                .withFlag(Tile.Flag.OCCUPIED)
                .fetchCount();     
    }
    
    /**
     * Get the capacity of a zone
     * @param type
     * @return 
     */
    protected int capacityOf(Tile.Zone type)
    {
        return map.new Query().withType(type).fetchCount();           
    }
    
    /**
     * Get the number of jobs available
     * @return int
     */
    protected int availableJobs()
    {
        return map.new Query().withType(Tile.Zone.INDUSTRIAL)
                .withType(Tile.Zone.COMMERCIAL)
                .withoutFlag(Tile.Flag.OCCUPIED)
                .fetchCount();  
    }
    
    /**
     * Get the number of jobs occupied
     * @return int
     */
    protected int occupiedJobs()
    {
        return map.new Query().withType(Tile.Zone.INDUSTRIAL)
                .withType(Tile.Zone.COMMERCIAL)
                .withFlag(Tile.Flag.OCCUPIED)
                .fetchCount();  
    }
}
