/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.ui;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import javacity.world.Map;
import javacity.world.Tile;
import javacity.world.Type;
import javax.imageio.ImageIO;

/**
 *
 * @author Tom
 */
public class Viewport extends Canvas implements Runnable {
    
    private Map c;
    private EnumMap<Type, Image> images;
    
    public Viewport(Map c)
    {
        super();
        this.c = c;
        
        try {
            images = new EnumMap<Type,Image>(Type.class);
            images.put(Type.RESIDENTIAL, ImageIO.read(new File("zone_r.png")));
            images.put(Type.COMMERCIAL, ImageIO.read(new File("zone_c.png")));
            images.put(Type.INDUSTRIAL, ImageIO.read(new File("zone_i.png")));   
            
            images.put(Type.GRASS,  ImageIO.read(new File("grass.png")));   
            images.put(Type.ROAD,  ImageIO.read(new File("road.png")));   
            
            images.put(Type.OCCUPIED_RESIDENTIAL, ImageIO.read(new File("occupied_r.png")));
            images.put(Type.OCCUPIED_COMMERCIAL, ImageIO.read(new File("occupied_c.png")));
            images.put(Type.OCCUPIED_INDUSTRIAL, ImageIO.read(new File("occupied_i.png")));   
            
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
                
                if (this.images.containsKey(t.getType())) {
                    g2.drawImage(this.images.get(t.getType()),x,y,this);
                } else {
                    g2.setColor(Color.gray);
                    g2.fillRect(x, y, 32, 32);
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
