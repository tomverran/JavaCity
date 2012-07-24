/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.ui;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javacity.world.Map;
import javacity.world.Tile;

/**
 *
 * @author Tom
 */
public class Viewport extends Canvas implements Runnable {
    
    private Map c;
    private ImageRepository i;
    
    public Viewport(Map c, ImageRepository i)
    {
        super();
        this.c = c;
        this.i = i;
    }
    
    public void init()
    {
        this.setPreferredSize(new Dimension(c.getXSize()*32,c.getYSize()*32));
        this.setVisible(true);
        
        this.createBufferStrategy(2);
        this.setIgnoreRepaint(true);        
    }
    
    public void draw()
    {
        Graphics2D g2 = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
        
        for (int x = 0; x < c.getXSize()*32; x+=32) {
            for (int y = 0; y < c.getYSize()*32; y+=32) {
                Tile t = c.getByLocation(x/32, y/32);
                g2.drawImage(i.getImageFor(t),x,y,this);
                if (t.hasBuilding()) {
                    g2.drawImage(i.getImageFor(t.getBuilding()), x, y, this);
                }
            }
        }
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
}
