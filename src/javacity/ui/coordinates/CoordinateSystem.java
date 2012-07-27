package javacity.ui.coordinates;
import javacity.lib.Point;

/**
 * A coordinate system which governs the placement
 * of tiles on the screen and the translation of mouse coordinates
 * into tiles based on their position.
 * @author Tom
 */
public abstract class CoordinateSystem {
    
    public enum Order {
        ASCENDING, DESCENDING;
    }
    
    protected Point mapSize;
    protected int xShift;
    protected int yShift;
    
    /**
     * Convert a screen position to a tile point
     * @param screen
     * @return 
     */
    public abstract Point screenToTile(Point screen);
    
    /**
     * Convert a tile position to a screen point
     * @param tile
     * @return 
     */
    public abstract Point tileToScreen(Point tile);
    
    /**
     * Get the order in which to show things on the Y axis
     * @return 
     */
    public abstract Order getYOrder();
    
    /**
     * Get the order in which to show things on the X axis
     * @return 
     */
    public abstract Order getXOrder();
    public Point get() { return new Point(1,1); };
    public void setXShift(int xShift)
    {
        this.xShift = xShift;
    }
    
    public void setYShift(int yShift)
    {
        this.yShift = yShift;
    }
    
    public int getXShift()
    {
        return this.xShift;
    }
    
    public int getYShift()
    {
        return this.yShift;
    }
}
