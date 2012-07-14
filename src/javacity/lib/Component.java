package javacity.lib;
import javacity.world.City;
/**
 * A component of the game, which ticks.
 * @author Tom
 */
public abstract class Component 
{    
    private City c;
    
    /**
     * Construct it.
     * @param City c 
     */
    public Component(City c)
    {
        this.c = c;
    }
    
    /**
     * Get the city we're dealing with
     * @return 
     */
    public City getCity()
    {
        return this.c;
    }
    
    /**
     * Do something.
     */
    public void tick() {}  
}
