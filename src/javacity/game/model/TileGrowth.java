/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.model;

import java.util.Random;
import javacity.world.Tile;

/**
 *
 * @author Tom
 */
public abstract class TileGrowth extends AbstractModel
{
    private static Random r;
    private Tile.Zone zone;
    
    static {
        r = new Random();
    }
    
    /**
     * Init
     * @param City c
     * @param String zone type of the empty tile
     * @param String occupied type of the tile when occupied 
     */
    public TileGrowth(Tile.Zone zone)
    {
        this.zone = zone;
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
            this.occupy(zone);
        } else if (growth < -0.4) {
            this.desert(zone);    
        }
        
    }
    
    /**
     * Get a modifier for our random growth float
     * based on user-defined criteria.
     * @return 
     */
    public abstract float getGrowthModifier();
}
