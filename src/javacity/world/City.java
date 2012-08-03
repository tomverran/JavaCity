package javacity.world;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * A class to handle calculating metrics
 * about a city, such as population.
 * Essentially a load of global functions, so shoot me.
 * @author Tom
 */
public class City {
    
    private Map map;
    private BuildingRepository buildings;
    private static final Random r = new Random();
    private GregorianCalendar date;
    
    /**
     * Construct this city.
     * @param map
     * @param b 
     */
    public City(Map map, BuildingRepository b)
    {
        this.map = map;
        this.buildings = b;
        this.date = new GregorianCalendar();
        this.date.set(1900, 1, 1);
    }
    
    public GregorianCalendar getDate()
    {
        return date;
    }
    
    public void setDate(GregorianCalendar c)
    {
        this.date = c;
    }
    
    /**
     * Get residential population
     * @return 
     */
    public int population()
    {
        return this.population(Type.RESIDENTIAL);
    }
    
    /**
     * Get population of a certain type
     * @param t
     * @return 
     */
    public int population(Type t)
    {
        return map.getTiles(t,true).size();        
    }
    
    /**
     * Get the number of buildings the city has room for
     * @param t
     * @return 
     */
    public int buildingCapacity(Type t)
    {
        return map.getTiles(t, false).size();
    }
    
    /**
     * Get the number of jobs available
     * @return 
     */
    public int availableJobs()
    {
        return this.buildingCapacity(Type.INDUSTRIAL) + 
               this.buildingCapacity(Type.COMMERCIAL);
    }
    
    /**
     * Get the number of occupied jobs
     * @return 
     */
    public int occupiedJobs()
    {
        return population(Type.COMMERCIAL) + 
               population(Type.INDUSTRIAL);      
    }
    
    /**
     * Build a new building of the given type.
     * @param t 
     */
    public void occupy(Type t)
    {
        ArrayList<Tile> tiles = map.getTiles(t, false);
        if (tiles.size() > 0) {
            
            Tile tile = tiles.get(r.nextInt(tiles.size()));
            
            if (tile.hasBuilding()) {
                tile.getBuilding().setOccupied(true);
            } else {
                //Building building = buildings.getRandomBuilding(t);
                //tile.setBuilding(building);                 
            }          
        }
    }
    
    /**
     * Demolish a building of the given type.
     * @param t 
     */
    public void abandon(Type t)
    {
        ArrayList<Tile> tile = map.getTiles(t, true);
        if (tile.size() > 0) {
            tile.get(r.nextInt(tile.size())).getBuilding().setOccupied(false);            
        }
    }
}
