package javacity.game.component;
import javacity.world.City;
import javacity.world.Metrics;

/**
 * Class to handle growth and decline of industrial tiles.
 * @author Tom
 */
public class Industrial extends TileGrowth
{
    public Industrial(City c)
    {
        super(c, "zone_i", "occupied_i");
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = Metrics.population(this.city);
        int industrial = this.city.getTilesByType("occupied_i").size();
        return industrial > population ? -0.2f : 0.2f;  
    }
    
}
