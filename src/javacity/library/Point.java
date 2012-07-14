package javacity.library;

/**
 * An immutable, integer only alternative to java.awt.geom.Point2D.
 * @author Tom
 */
public class Point 
{
    private int x, y;
    
    /**
     * Initialise our point
     * @param int x
     * @param int y 
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get our Y co-ordinate
     * @return int
     */
    public int getX()
    {
        return this.x;
    }
    
    /**
     * Get our X co-ordinate
     * @return int
     */
    public int getY()
    {
        return this.y;
    }
    
    /**
     * Get the distance between this point and another.
     * @param Point p the point to get distance to
     * @return double the distance.
     */
    public double distanceTo(Point p)
    {
        int dx = Math.abs(this.x - p.x);
        int dy = Math.abs(this.y - p.y);
        
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}
