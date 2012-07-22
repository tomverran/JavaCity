package javacity.game.component;
import javacity.world.City;
import javacity.world.data.Zone;

/**
 * Class to handle growth and decline of commercial tiles.
 * @author Tom
 */
public class Commercial extends TileGrowth
{
    public Commercial(City c)
    {
        super(c, Zone.COMMERCIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.city.population();
        int commercial = this.city.populationOf(Zone.COMMERCIAL);
        int industrial = this.city.populationOf(Zone.INDUSTRIAL);
        
        //a commercial place requires two industrial buildings for supply
        boolean balanceOkay = (commercial + 1) * 2 < industrial;
        
        return balanceOkay && population > commercial ? 0.2f : -0.2f;  
    }
    
}
