/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javacity.world.Map;
import javacity.world.Tile;
import javacity.world.Tile.Zone;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Tom
 */
public class Toolbox extends JPanel implements MouseListener {
    
    private Map city;
    
    private Zone type;
    
    private int dragX, dragY;
    
    public Toolbox(Map city)
    {
        super();
        this.city = city;
        this.type = Zone.RESIDENTIAL;
        
        this.setLayout(new GridLayout(Tile.Zone.values().length,0));
        
        int i = 0;
        for (final Tile.Zone z : Tile.Zone.values()) {
            JButton button = new JButton(z.toString());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    type = z;
                }
            });
            this.add(button,0,i);
            i++;
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        int curX = e.getX() / 32;
        int curY = e.getY() / 32;
        
        int startx = Math.min(curX, this.dragX);
        int endx = Math.max(curX, this.dragX);
        
        int starty = Math.min(curY, this.dragY);
        int endy = Math.max(curY, this.dragY);
        
        for (int x = startx; x <= endx; x++) {
            for (int y = starty; y <= endy; y++) {
                if (this.city.isValidLocation(x, y)) {
                    this.city.getByLocation(x, y).setType(this.type);                    
                }
            }
        }
        
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        this.dragX = e.getX() / 32;
        this.dragY = e.getY() / 32;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }
}
