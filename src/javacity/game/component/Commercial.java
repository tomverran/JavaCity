/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;
import javacity.world.City;
import javacity.world.Metrics;

/**
 * Class to handle growth and decline of commercial tiles.
 * @author Tom
 */
public class Commercial extends TileGrowth
{
    public Commercial(City c)
    {
        super(c, "zone_c", "occupied_c");
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = Metrics.population(this.city);
        int commercial = this.city.getTilesByType("occupied_c").size();
        int industrial = this.city.getTilesByType("occupied_i").size();
        
        //a commercial place requires two industrial buildings for supply
        boolean balanceOkay = (commercial + 1) * 2 < industrial;
        
        return balanceOkay && population > commercial ? 0.2f : -0.2f;  
    }
    
}
