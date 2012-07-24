package javacity.game.component;
import javacity.world.Map;
import javacity.world.City;
import javacity.world.Type;

/**
 * Class to handle growth and decline of industrial tiles.
 * @author Tom
 */
public class Industrial extends TileGrowth
{
    public Industrial(City c, Map m)
    {
        super(m, c, Type.INDUSTRIAL, Type.OCCUPIED_INDUSTRIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.city.population();
        int industrial = this.map.getTilesByType(Type.OCCUPIED_INDUSTRIAL).size();
        return industrial > population ? -0.2f : 0.2f;  
    }
    
}
