/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.ui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Tom
 */
public class GuiToolbox extends JPanel {
    
    public GuiToolbox()
    {
        this.setLayout(new GridLayout(3,1));
        this.add(new JButton("Residential"),0,0);
        this.add(new JButton("Commerical"),1,0);
        this.add(new JButton("Industrial"),2,0);
        
    }
    
}
