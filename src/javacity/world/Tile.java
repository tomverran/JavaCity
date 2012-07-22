package javacity.world;
import java.util.EnumSet;
import java.util.Observable;
/**
 * A tile in our city. Knows its type. Soon to store other data.
 * @author Tom
 */
public class Tile extends Observable 
{
    public static enum Flag {

        OCCUPIED("Occupied"),
        POWERED("Has Electricity");

        private String name;

        /**
         * Initialise a new flag.
         * @param name 
         */
        private Flag(String name)
        {
            this.name = name;
        }

        /**
         * Get the name of this Flag
         * @return String
         */
        @Override
        public String toString()
        {
            return this.name;
        }
    }    
    
    public static enum Zone {
        COMMERCIAL("Commercial"), 
        RESIDENTIAL("Residential"),
        INDUSTRIAL("Industrial"),
        POWERPLANT("Power Plant"),
        GRASS("Grass"),
        ROAD("Road");

        private String name;

        private Zone(String name) {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return this.name;
        }
    }

    private Zone type;
    private EnumSet<Flag> flags;
    
    /**
     * Construct our tile.
     */
    public Tile()
    {
        this.flags = EnumSet.noneOf(Flag.class);
    }

    /**
     * Give this tile a flag
     * @param flag 
     */
    public void addFlag(Flag flag)
    {
        this.flags.add(flag);
    }
    
    /**
     * Does this tile have the given flag?
     * @param flag
     * @return 
     */
    public boolean hasFlag(Flag flag)
    {
        return this.flags.contains(flag);
    }
    
    /**
     * Remove the given flag from the tile
     * @param flag 
     */
    public void unsetFlag(Flag flag)
    {
        this.flags.remove(flag);
    }
    
    /**
     * Set our type
     * @param type 
     */
    public void setType(Zone type)
    {
        Zone oldType = this.type;
        this.type = type;
        this.setChanged();
        this.notifyObservers(oldType);
    }
    
    /**
     * Get our type
     * @return 
     */
    public Zone getType()
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
