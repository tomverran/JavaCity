package javacity.ui;
import java.awt.BorderLayout;
import javacity.world.City;
import javacity.world.Metrics;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A prettier GUI
 * @author Tom
 */
public class Gui extends JFrame {
    
    private GuiCanvas canvas;
    private GuiToolbox tools;
    private JLabel pop;
    private City city;
    
    public Gui(City c)
    {
        super();
        this.city = c;
        this.setVisible(true);
        this.canvas = new GuiCanvas(c);
        this.tools = new GuiToolbox(c);
        this.pop = new JLabel("Population: 0");
        
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(this.tools,BorderLayout.WEST);
        
        this.add(this.canvas,BorderLayout.CENTER);
        this.add(this.pop,BorderLayout.SOUTH);
        
        this.canvas.addMouseListener(this.tools);
        this.canvas.init();
        this.pack();

        this.setLocationRelativeTo(null);
        this.setTitle("JavaCity");
    }
    
    public void updateCanvas()
    {
        this.canvas.draw();
        this.pop.setText("Population: "+Metrics.population(this.city));
    }
    
    public void repaint()
    {
        super.repaint();
        this.canvas.draw();
    }
    
}
