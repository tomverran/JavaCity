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
        int xPos = (screen.getX() - this.xShift - 16) / 32;
        int yPos = (screen.getY() - this.yShift) / 16;

        yPos = yPos - mapSizeX; // y - x
        xPos = (xPos - yPos) / 2;
        yPos = yPos + xPos;
        System.out.println("X: "+xPos+" Y: "+yPos);
        
        if(((screen.getX()-this.xShift) % 32) >= 16) {
            if(((screen.getY()-this.yShift) % 16) < 8) {
                xPos++;
            }
        } else {
            if(((screen.getY()-this.yShift) % 16) < 8) {
                xPos++;
            }
        }
        System.out.println("--> X: "+xPos+" Y: "+yPos);
        //todo fix D:
        return new Point(xPos, yPos);
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
