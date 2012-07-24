/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;
import javacity.world.Map;
import javacity.world.City;
import javacity.world.Type;

/**
 * Class to handle growth and decline of commercial tiles.
 * @author Tom
 */
public class Commercial extends TileGrowth
{
    public Commercial(City c, Map m)
    {
        super(m, c, Type.COMMERCIAL, Type.OCCUPIED_COMMERCIAL);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int population = this.city.population();
        int commercial = this.map.getTilesByType(Type.OCCUPIED_COMMERCIAL).size();
        int industrial = this.map.getTilesByType(Type.OCCUPIED_INDUSTRIAL).size();
        
        //a commercial place requires two industrial buildings for supply
        boolean balanceOkay = (commercial + 1) * 2 < industrial;
        
        return balanceOkay && population > commercial ? 0.2f : -0.2f;  
    }
    
}
