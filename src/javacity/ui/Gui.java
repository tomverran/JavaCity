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
        this.canvas = new GuiCanvas(c);
        this.tools = new GuiToolbox(c);
        this.pop = new JLabel("Population: 0");
        
        //set our properties.
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("JavaCity");
        this.setResizable(false);

        //add the GUI elements to our frame
        this.add(this.tools,BorderLayout.WEST);
        this.add(this.canvas,BorderLayout.CENTER);
        this.add(this.pop,BorderLayout.SOUTH);
        this.setVisible(true);
        
        //initialise our Canvas, must be done afer adding for bufferStrategy
        this.canvas.addMouseListener(this.tools);
        this.canvas.init();
        this.pack();

        
        //begin our animation loop.
        Thread animator = new Thread(canvas);
        animator.start();
    }    
}
