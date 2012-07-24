package javacity.world;
import java.util.Observable;
/**
 * A tile in our city. Knows its type. Soon to store other data.
 * @author Tom
 */
public class Tile extends Observable 
{
    private Type type;
    private Building building;
 
    /**
     * Set our type
     * @param type 
     */
    public void setType(Type type)
    {
        Type oldType = this.type;
        this.type = type;
        this.setChanged();
        this.notifyObservers(oldType);
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
