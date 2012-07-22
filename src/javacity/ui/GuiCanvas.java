package javacity.ui;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.EnumMap;
import javacity.world.Map;
import javacity.world.Tile;
import javacity.world.data.Flag;
import javacity.world.data.Zone;
import javax.imageio.ImageIO;

/**
 * A Java awt canvas responsible for drawing the city.
 * @author Tom
 */
public class GuiCanvas extends Canvas implements Runnable {
    
    private Map c;
    private EnumMap<Zone, Image> images;
    private EnumMap<Zone, Image> buildings;
    
    public GuiCanvas(Map c)
    {
        super();
        this.c = c;
        
        try {
            images = new EnumMap<Zone,Image>(Zone.class);
            buildings = new EnumMap<Zone,Image>(Zone.class);
            
            images.put(Zone.RESIDENTIAL, ImageIO.read(new File("zone_r.png")));
            images.put(Zone.COMMERCIAL, ImageIO.read(new File("zone_c.png")));
            images.put(Zone.INDUSTRIAL, ImageIO.read(new File("zone_i.png")));   
            images.put(Zone.GRASS, ImageIO.read(new File("grass.png")));   
            
            buildings.put(Zone.RESIDENTIAL, ImageIO.read(new File("occupied_r.png")));
            buildings.put(Zone.COMMERCIAL, ImageIO.read(new File("occupied_c.png")));
            buildings.put(Zone.INDUSTRIAL, ImageIO.read(new File("occupied_i.png")));   
      
        } catch (Exception e) {
            System.out.println("fail");
        }
        

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
                
                //draw the basic tile graphic.
                if (this.images.containsKey(t.getType())) {
                    g2.drawImage(this.images.get(t.getType()),x,y,this);
                } else {
                    g2.setColor(Color.gray);
                    g2.fillRect(x, y, 32, 32);
                }
                
                //special case: buildings
                if (t.hasFlag(Flag.OCCUPIED)) {
                    g2.drawImage(this.buildings.get(t.getType()), x,y, this);
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
