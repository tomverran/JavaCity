package javacity.world;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
import javacity.world.data.Flag;
import javacity.world.data.Zone;

/**
 * A class to provide a richer
 * querying interface to a Map.
 * @author Tom
 */
public class Query 
{
    private Map map;
    private EnumSet<Zone> selectTypes;
    private EnumSet<Flag> selectFlags;
    private EnumSet<Flag> selectNotFlags;  
    private static Random r;
    
    static {
        r = new Random();
    }
    
    /**
     * Create our query
     * @param m The map to query 
     */
    public Query(Map m)
    {
        this.map = m;
        this.selectTypes = EnumSet.noneOf(Zone.class);
        this.selectFlags = EnumSet.noneOf(Flag.class);
        this.selectNotFlags = EnumSet.noneOf(Flag.class);
    }
    
    /**
     * Add a flag criteria
     * @param flag
     * @return 
     */
    public Query withFlag(Flag flag)
    {
        this.selectFlags.add(flag);
        return this;
    }
    
    /**
     * Add a without flag criteria
     * @param flag
     * @return 
     */
    public Query withoutFlag(Flag flag)
    {
        this.selectNotFlags.add(flag);
        return this;
    }
    
    /**
     * Add a type criteria
     * @param type
     * @return 
     */
    public Query withType(Zone type)
    {
        this.selectTypes.add(type);
        return this;
    }
    
    /**
     * Fetch all tiles matching the criteria
     * @return an array of tiles
     */
    public ArrayList<Tile> fetchAll()
    {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        ArrayList<Tile> append;
        
        for (Zone zone : this.selectTypes) {
            
            append = map.getTilesByType(zone);
            if (this.selectFlags.size() > 0 || this.selectNotFlags.size() > 0) {
                for (Tile tile : append) {
                    boolean okay = true;
                    for (Flag flag: this.selectFlags) {
                        if (!tile.hasFlag(flag)) {
                            okay = false;
                            break;
                        }
                    }
                    for (Flag flag : this.selectNotFlags) {
                        if (tile.hasFlag(flag)) {
                            okay = false;
                            break;
                        }
                    }
                    if (!okay) {
                        continue;
                    }
                    tiles.add(tile);
                }                
            } else {
                tiles.addAll(append);
            }
        }
        return tiles;
    }
    
    /**
     * Fetch the number of tiles matching the criteria
     * @return the count of the tiles
     */
    public int fetchCount()
    {
        return this.fetchAll().size();
    }
    
    /**
     * Fetch a random tile matching the criteria
     * @return a random tile
     */
    public Tile fetchRandom()
    {
        ArrayList<Tile> tiles = this.fetchAll();
        if (tiles.size() > 0) {
            return tiles.get(Query.r.nextInt(tiles.size()));   
        } else {
            return null;
        }
    }
}
