package javacity.game.component;
import java.util.Random;
import javacity.lib.Component;
import javacity.world.City;
import javacity.world.Type;

/**
 *
 * @author Tom
 */
public abstract class TileGrowth implements Component 
{
    private Type zone;
    protected City city;  
    private static Random r = new Random();

    
    /**
     * Init
     * @param Map c
     * @param String zone type of the empty tile
     * @param String occupied type of the tile when occupied 
     */
    public TileGrowth(City c, Type zone)
    {
        this.city = c;
        this.zone = zone;
    }
    
    /**
     * Run every simulation iteration,
     * grow tiles based on our stuff
     */
    @Override
    public void tick()
    {
        float growth = r.nextFloat() - 0.5f;
        growth += this.getGrowthModifier();
        
        if (growth > 0.3) {
            this.city.build(zone);
        } else if (growth < -0.3) {
            this.city.demolish(zone);             
        }
    }
    
    /**
     * Get a modifier for our random growth float
     * based on user-defined criteria.
     * @return 
     */
    public abstract float getGrowthModifier();
}
