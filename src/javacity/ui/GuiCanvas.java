/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.ui;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.HashMap;
import javacity.world.City;
import javacity.world.Tile;

/**
 *
 * @author Tom
 */
public class GuiCanvas extends Canvas {
    
    private City c;
    private HashMap<String, Color> colours;
    
    public GuiCanvas(City c)
    {
        super();
        this.c = c;
        
        colours = new HashMap<String,Color>();
        colours.put("zone_r", Color.green);
        colours.put("zone_c", Color.blue);
        colours.put("zone_i", Color.yellow);   
        colours.put("grass", Color.cyan);
    }
    
    public void init()
    {
        this.setPreferredSize(new Dimension(640,480));
        this.setVisible(true);
        
        this.createBufferStrategy(2);
        this.setIgnoreRepaint(true);        
    }
    
    public void draw()
    {
        Graphics2D g2 = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
        
        for (int x = 0; x < c.getSize()*32; x+=32) {
            for (int y = 0; y < c.getSize()*32; y+=32) {
                Tile t = c.getByLocation(x/32, y/32);
                
                if (this.colours.containsKey(t.getType())) {
                    g2.setColor(this.colours.get(t.getType()));
                } else {
                    g2.setColor(Color.gray);
                }
                
                g2.fillRect(x, y, 32, 32);
            }
        }
        
        this.getBufferStrategy().show();
    }
    
}
