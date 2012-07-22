package javacity.game.model;
import javacity.world.Tile;

/**
 * Class to handle growth and decline of commercial tiles.
 * @author Tom
 */
public class Commercial extends TileGrowth
{
    public Commercial()
    {
        super(Tile.Zone.COMMERCIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.population();
        int commercial = this.populationOf(Tile.Zone.COMMERCIAL);
        int industrial = this.populationOf(Tile.Zone.INDUSTRIAL);
        
        //a commercial place requires two industrial buildings for supply
        boolean balanceOkay = (commercial + 1) * 2 < industrial;
        
        return balanceOkay && population > commercial ? 0.2f : -0.2f;  
    }
    
}
