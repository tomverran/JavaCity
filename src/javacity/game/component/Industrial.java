package javacity.game.component;
import javacity.world.City;
import javacity.world.Type;

/**
 * Class to handle growth and decline of industrial tiles.
 * @author Tom
 */
public class Industrial extends TileGrowth
{
    public Industrial(City c)
    {
        super(c, Type.INDUSTRIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.city.population();
        int industrial = this.city.population(Type.INDUSTRIAL);
        return industrial > population ? -0.2f : 0.2f;  
    }
    
}
