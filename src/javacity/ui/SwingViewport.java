package javacity.ui;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javacity.lib.Point;
import javacity.ui.coordinates.CoordinateSystem;
import javacity.world.Map;
import javacity.world.Tile;

/**
 * A ViewPort which, in this case, is isometric.
 * @author Tom
 */
public class SwingViewport extends Canvas implements Runnable, KeyListener {
    
    private Map c;
    private ImageRepository i;
    private CoordinateSystem coords;

    /**
     * Construct our viewport.
     * @param c
     * @param i 
     */
    public SwingViewport(Map c, ImageRepository i, CoordinateSystem coords)
    {
        super();
        this.coords = coords;
        this.c = c;
        this.i = i;
    }
    
    /**
     * Initialise our viewport
     */
    public void init()
    {
        this.setPreferredSize(new Dimension(c.getXSize()*32,c.getYSize()*32));
        this.setVisible(true);
        
        this.createBufferStrategy(2);
        this.setIgnoreRepaint(true);  
        this.addKeyListener(this);
    }
    
    /**
     * Draw tiles to the screen in positions
     * dictated by the current coordinate system
     */
    public void draw()
    {
        //grab the graphics context upon which to draw our map.
        Graphics2D g2 = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
        
        //clear screen
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        //init variables needed for coordinates.
        int yStart, yStep, yEnd, xStart, xEnd, xStep;

        //set up our start, end and step coordinates based on the system
        if (coords.getYOrder() == CoordinateSystem.Order.DESCENDING) {
            yStart = c.getYSize() - 1;
            yStep = -1;
            yEnd = 0;
        } else {
            yEnd = c.getYSize();
            yStart = 0;
            yStep = 1;
        }
        
        //do the same as the above but for X co-ordinates.
        if (coords.getXOrder() == CoordinateSystem.Order.DESCENDING) {
            xStart = c.getXSize() - 1;
            xStep = -1;
            xEnd = -1;
        } else {
            xEnd = c.getXSize();
            xStart = 0;
            xStep = 1;
        }
        
        //loop through every X and Y coordinate with the order defined.
        for (int y = yStart; (yEnd > yStart) ? y < yEnd : y > yEnd ; y+=yStep) {
            for (int x = xStart; (xEnd > xStart) ? x < xEnd : x > xEnd ; x+=xStep) {

                //ask our co-ordinate system where we should draw the tile
                Point position = this.coords.tileToScreen(new Point(x, y));
                int xPos = position.getX();
                int yPos = position.getY();

                g2.drawLine(40, 40, 45, 45);
                
                //now actually draw it.
                Tile t = c.getByLocation(x, y);
                g2.drawImage(i.getImageFor(t), xPos, yPos, this);

                if (t.hasBuilding()) {
                    Image img = i.getImageFor(t.getBuilding());
                    int yp = yPos-(img.getHeight(this)-32);
                    g2.drawImage(i.getImageFor(t.getBuilding()), xPos, yp, this);
                }
            }
        }
        
        //flush the buffer to the screen
        this.getBufferStrategy().show();
    }
    
    /**
     * Run our animation thread
     * that constantly redraws the city.
     */
    @Override
    public void run()
    {
        while (true) {       
            draw();
            try {
                Thread.sleep(20);                    
            } catch (InterruptedException e) {
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent o)
    {
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent o) 
    {
        if (o.getKeyCode() == KeyEvent.VK_UP) {
            this.coords.setYShift(this.coords.getYShift()+5);
        } else if (o.getKeyCode() == KeyEvent.VK_DOWN) {
            this.coords.setYShift(this.coords.getYShift()-5);
        } else if (o.getKeyCode() == KeyEvent.VK_LEFT) {
            this.coords.setXShift(this.coords.getXShift()+5);
        } else if (o.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.coords.setXShift(this.coords.getXShift()-5);
        }
    }
}
