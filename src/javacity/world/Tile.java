package javacity.world;
import java.util.Observable;
/**
 * A tile in our city. Knows its type. Soon to store other data.
 * @author Tom
 */
public class Tile extends Observable 
{
    private String type;
 
    /**
     * Set our type
     * @param type 
     */
    public void setType(String type)
    {
        String oldType = this.type;
        this.type = type;
        this.setChanged();
        this.notifyObservers(oldType);
    }
    
    /**
     * Get our type
     * @return 
     */
    public String getType()
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
