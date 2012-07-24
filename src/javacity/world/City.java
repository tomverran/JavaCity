package javacity.world;

import java.util.ArrayList;
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
    
    public City(Map map, BuildingRepository b)
    {
        this.map = map;
        this.buildings = b;
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
    public void build(Type t)
    {
        ArrayList<Tile> tile = map.getTiles(t, false);
        if (tile.size() > 0) {
            Building building = buildings.getRandomBuilding(t);
            tile.get(r.nextInt(tile.size())).setBuilding(building);           
        }
    }
    
    /**
     * Demolish a building of the given type.
     * @param t 
     */
    public void demolish(Type t)
    {
        ArrayList<Tile> tile = map.getTiles(t, true);
        if (tile.size() > 0) {
            tile.get(r.nextInt(tile.size())).removeBuilding();            
        }
    }
}
