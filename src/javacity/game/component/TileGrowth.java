/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;

import java.util.Random;
import javacity.lib.Component;
import javacity.world.City;

/**
 *
 * @author Tom
 */
public abstract class TileGrowth implements Component 
{
    protected City city;
    private static Random r;
    private String zone;
    private String occupied;
    
    static {
        r = new Random();
    }
    
    /**
     * Init
     * @param City c
     * @param String zone type of the empty tile
     * @param String occupied type of the tile when occupied 
     */
    public TileGrowth(City c, String zone, String occupied)
    {
        this.city = c;
        this.zone = zone;
        this.occupied = occupied;
    }
    
    /**
     * Run every simulation iteration,
     * grow tiles based on our stuff
     */
    @Override
    public void tick()
    {
        float growth = r.nextFloat() - 0.5f;
        growth += this.getGrowthModifier();
        
        if (growth > 0.4) {
            this.city.switchTypeOfRandom(this.zone, this.occupied);
        } else if (growth < -0.4) {
            this.city.switchTypeOfRandom(this.occupied, this.zone);             
        }
        
    }
    
    /**
     * Get a modifier for our random growth float
     * based on user-defined criteria.
     * @return 
     */
    public abstract float getGrowthModifier();
}
