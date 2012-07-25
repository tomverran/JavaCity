package javacity.ui;
import java.awt.BorderLayout;
import javacity.lib.Component;
import javacity.ui.strategy.coordinates.CoordinateSystem;
import javacity.ui.strategy.coordinates.Isometric;
import javacity.ui.strategy.coordinates.TopDown;
import javacity.world.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A prettier GUI
 * @author Tom
 */
public class Gui extends JFrame implements Component {
    
    public enum Mode {
        ISOMETRIC, TOPDOWN
    }
    
    private Viewport canvas;
    private Toolbox tools;
    private JLabel pop;
    private Map city;
    
    /**
     * Initialise our GUI, assembling a Canvas for drawing
     * and a Toolbox for event handling. Start the animation thread.
     * @param c 
     */
    public Gui(Map c, Mode m)
    {
        super();
        this.city = c;
        
        String tileset;
        CoordinateSystem coords;
        
        if (m == Mode.ISOMETRIC) {
            coords = new Isometric(c.getXSize(),c.getYSize());
            tileset = "isometric";
        } else {
            coords = new TopDown();
            tileset = "topdown";
        }
        
        this.canvas = new Viewport(c, new ImageRepository(tileset), coords);
        this.tools = new Toolbox(c, coords);
        
        this.pop = new JLabel("Population: is broken");
        
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
        //this.pop.setText("Population: "+City.population(city));
    }
}
