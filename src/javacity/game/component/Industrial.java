package javacity.game.component;
import javacity.world.City;
import javacity.world.data.Zone;

/**
 * Class to handle growth and decline of industrial tiles.
 * @author Tom
 */
public class Industrial extends TileGrowth
{
    public Industrial(City c)
    {
        super(c, Zone.INDUSTRIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.city.population();
        int industrial = this.city.populationOf(Zone.INDUSTRIAL);
        System.out.println(population + ", "+industrial);
        return industrial > population ? -0.2f : 0.2f;  
    }
    
}
