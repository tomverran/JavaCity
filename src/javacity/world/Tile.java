package javacity.world;

/**
 * A tile in our city. Knows its type. Soon to store other data.
 * @author Tom
 */
public class Tile
{
    private Building building;
    private Terrain terrain;
 
    Tile() {
        terrain = Terrain.GRASS;
    }
    
    /**
     * Set the building on our tile
     * @param building 
     */
    public void setBuilding(Building building)
    {
        this.building = building;
    }
    
    public void removeBuilding()
    {
        this.building = null;
    }
    
    /**
     * Do we have a building?
     * @return 
     */
    public boolean hasBuilding()
    {
        return this.building != null;
    }
    
    public Building getBuilding()
    {
        return this.building;
    }
    
    public Terrain getTerrain() {
        return this.terrain;
    }
    
    public void setTerrain(Terrain t) {
        this.terrain = t;
    }
}
