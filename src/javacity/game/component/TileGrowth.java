/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;

import java.util.Random;
import javacity.lib.Component;
import javacity.world.City;
import javacity.world.Map;
import javacity.world.Type;

/**
 *
 * @author Tom
 */
public abstract class TileGrowth implements Component 
{
    protected Map map; //todo remove
    protected City city;
    
    private Type zone;
    private Type occupied;
    
    private static Random r;
    static {
        r = new Random();
    }
    
    /**
     * Init
     * @param Map c
     * @param String zone type of the empty tile
     * @param String occupied type of the tile when occupied 
     */
    public TileGrowth(Map m, City c, Type zone, Type occupied)
    {
        this.map = m;
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
            this.map.switchTypeOfRandom(this.zone, this.occupied);
        } else if (growth < -0.4) {
            this.map.switchTypeOfRandom(this.occupied, this.zone);             
        }
        
    }
    
    /**
     * Get a modifier for our random growth float
     * based on user-defined criteria.
     * @return 
     */
    public abstract float getGrowthModifier();
}
