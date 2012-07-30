package javacity.ui.coordinates;
import javacity.lib.Point;

/**
 * A top down coordinate system
 * @author Tom
 */
public class TopDown extends CoordinateSystem 
{
    
    public Point screenToTile(Point screen) {
        return screenToTile(screen.getX(), screen.getY());
    }
    
    /**
     * Convert from screen space to tile space
     * @param screen
     * @return 
     */
    @Override
    public Point screenToTile(int x, int y) 
    {
        return new Point((x - xShift) / 32, (y - yShift) / 32);
    }

    /**
     * Convert from tile space to screen space
     * @param tile
     * @return 
     */
    @Override
    public Point tileToScreen(Point tile) 
    {
        return new Point((tile.getX() * 32) + xShift, (tile.getY() * 32) + yShift);
    }

    /**
     * Get Y axis render order
     * @return 
     */
    @Override
    public Order getYOrder() 
    {
        return Order.ASCENDING;
    }

    /**
     * Get X axis render order
     * @return 
     */
    @Override
    public Order getXOrder() 
    {
        return Order.ASCENDING;
    }
}
