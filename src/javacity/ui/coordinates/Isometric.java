package javacity.ui.coordinates;
import javacity.lib.Point;

/**
 * A coordinate system for isometric tiles.
 * @author Tom
 */
public class Isometric extends CoordinateSystem 
{
    private int mapSizeX;
    private int mapSizeY;
    
    /**
     * Construct this isometric coordinate system.
     * Because I suck at maths I want the map size.
     */
    public Isometric(int mapSizeX, int mapSizeY)
    {
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;
    }
    
    /**
     * Convert a screen coordinate to a tile coordinate
     * @param screen
     * @return 
     */
    @Override
    public Point screenToTile(Point screen) 
    {        
        //undo the effects of any map shifts
        float xPos = (screen.getX() - this.xShift) / 32.0f;
        float yPos = (screen.getY() - this.yShift) / 16.0f;
        yPos += 0.1f;
             
        yPos = yPos - mapSizeX + 1; // y - x
        xPos = (xPos - yPos) / 2;
        yPos = yPos + xPos;

        int x1 = (int) Math.ceil(xPos);
        int y1 = (int) Math.ceil(yPos);
        y1 -= 2;

        //todo fix D:
        return new Point(x1, y1);
    }
    
    /**
     * Convert a tile coordinate to a screen coordinate
     * @param tile
     * @return 
     */
    @Override
    public Point tileToScreen(Point tile) 
    {
        int xPos = (tile.getX()*32)+(tile.getY()*32)+this.xShift;
        int yPos = (tile.getY()*16)+((mapSizeX-tile.getX())*16)+this.yShift;
        return new Point(xPos, yPos);
    }

    /**
     * Get the order in which to render things on the Y axis
     * @return 
     */
    @Override
    public Order getYOrder() 
    {
        return Order.ASCENDING;
    }

    /**
     * See above, but for X.
     * @return 
     */
    @Override
    public Order getXOrder() 
    {
        return Order.DESCENDING;
    }
}
