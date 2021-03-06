package javacity.ui;
import java.awt.BorderLayout;
import javacity.lib.Component;
import javacity.world.City;
import javacity.world.Metrics;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A prettier GUI
 * @author Tom
 */
public class Gui extends JFrame implements Component {
    
    private GuiCanvas canvas;
    private GuiToolbox tools;
    private JLabel pop;
    private City city;
    
    /**
     * Initialise our GUI, assembling a Canvas for drawing
     * and a Toolbox for event handling. Start the animation thread.
     * @param c 
     */
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
        animator.start(); //shut up netbeans, its fine.
    }    
    
    /**
     * Update the population label
     * on each tick of the simulation
     */
    @Override
    public void tick()
    {
        this.pop.setText("Population: "+Metrics.population(city));
    }
}
