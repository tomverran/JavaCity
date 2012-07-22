package javacity.world.data;

/**
 * This is an enum type to denote
 * valid flags that can be given to tiles. A flag
 * has a system & human readable name. All flags are associated
 * with boolean values in an internal tile Array, but you don't know that.
 * @author Tom
 */
public enum Flag {
    
    OCCUPIED("Occupied");
    
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
