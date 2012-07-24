package javacity.world;

/**
 * A class to handle calculating metrics
 * about a city, such as population.
 * Essentially a load of global functions, so shoot me.
 * @author Tom
 */
public class City {
    
    private Map map;
    
    public City(Map map)
    {
        this.map = map;
    }
    
    public int population()
    {
        return map.getTilesByType(Type.RESIDENTIAL).size();
    }
    
    public int availableJobs()
    {
        return map.getTilesByType(Type.INDUSTRIAL).size() + 
               map.getTilesByType(Type.COMMERCIAL).size();
    }
    
    public int occupiedJobs()
    {
        return map.getTilesByType(Type.OCCUPIED_INDUSTRIAL).size() + 
               map.getTilesByType(Type.OCCUPIED_COMMERCIAL).size();        
    }
}
