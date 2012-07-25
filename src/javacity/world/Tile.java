package javacity.world;

/**
 * A tile in our city. Knows its type. Soon to store other data.
 * @author Tom
 */
public class Tile
{
    private Type type;
    private Building building;
 
    /**
     * Set our type
     * @param type 
     */
    public void setType(Type type)
    {
        if (type != this.type && this.hasBuilding()) {
            this.removeBuilding();
        }
        this.type = type;
    }
    
    /**
     * Set the building on our tile
     * @param building 
     */
    public void setBuilding(Building building)
    {
        if (building.getType() != this.getType()) {
            throw new IllegalArgumentException("Bad building type");
        }
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
    
    /**
     * Get our type
     * @return 
     */
    public Type getType()
    {
        return this.type;
    }
    
    /**
     * For debugging
     * @return 
     */
    @Override
    public String toString()
    {
        return "tile: "+this.type;
    }
}
