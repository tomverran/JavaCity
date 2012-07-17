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
import javacity.world.City;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Tom
 */
public class GuiToolbox extends JPanel implements MouseListener, ActionListener {
    
    private City city;
    
    private JButton r, c, i, road;
    
    private String type;
    
    public GuiToolbox(City city)
    {
        super();
        this.city = city;
        this.type = "zone_r";
        
        r = new JButton("Residential");
        c = new JButton("Commercial");
        i = new JButton("Industrial");
        road = new JButton("Road");
        
        this.setLayout(new GridLayout(4,0));
        this.add(r,0,0);
        this.add(c,1,0);
        this.add(i,2,0);   
        this.add(road,3,0);
        
        r.addActionListener(this);
        c.addActionListener(this);
        i.addActionListener(this);
        road.addActionListener(this);
        
        
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
        
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX() / 32;
        int y = e.getY() / 32;
        
        this.city.getByLocation(x, y).setType(this.type);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.r) {
            this.type ="zone_r";
        } else if (e.getSource() == this.c) {
            this.type = "zone_c";
        } else if (e.getSource() == this.i) {
            this.type = "zone_i";
        } else if (e.getSource() == this.road) {
            this.type = "road";
        }
    }
}
