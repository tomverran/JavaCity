package javacity.game.model;
import javacity.world.Tile;
import javacity.world.Tile.Zone;

/**
 * Class to handle growth and decline of industrial tiles.
 * @author Tom
 */
public class Industrial extends TileGrowth
{
    public Industrial()
    {
        super(Tile.Zone.INDUSTRIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.population();
        int industrial = this.populationOf(Zone.INDUSTRIAL);
        return industrial > population ? -0.2f : 0.2f;  
    }
}
