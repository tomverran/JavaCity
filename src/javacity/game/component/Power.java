/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;

import javacity.world.City;
import javacity.world.Type;

/**
 *
 * @author Ali
 */
public class Power extends TileGrowth {
    public Power(City c)
    {
        super(c, Type.POWER);
    }
    
    @Override
    public float getGrowthModifier()
    {
        int people = this.city.population();
        int industrial = this.city.population(Type.INDUSTRIAL);
        return industrial < people ? -0.2f : 0.2f;  
    }   
}
