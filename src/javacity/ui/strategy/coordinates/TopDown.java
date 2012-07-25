package javacity.ui.strategy.coordinates;
import javacity.lib.Point;

/**
 * A top down coordinate system
 * @author Tom
 */
public class TopDown extends CoordinateSystem 
{
    
    /**
     * Convert from screen space to tile space
     * @param screen
     * @return 
     */
    @Override
    public Point screenToTile(Point screen) 
    {
        return new Point((screen.getX() - xShift) / 32, (screen.getY() - yShift) / 32);
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
