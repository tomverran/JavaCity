package javacity.ui;
import java.awt.BorderLayout;
import java.text.DateFormat;
import javacity.lib.Component;
import javacity.ui.coordinates.CoordinateSystem;
import javacity.ui.coordinates.Isometric;
import javacity.ui.coordinates.TopDown;
import javacity.world.City;
import javacity.world.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A prettier GUI
 * @author Tom
 */
public class SwingGui extends JFrame implements Component {
    
    public enum Mode {
        ISOMETRIC, TOPDOWN
    }
    
    private Viewport canvas;
    private Toolbox tools;
    private JLabel pop;
    private City city;
    
    /**
     * Initialise our GUI, assembling a Canvas for drawing
     * and a Toolbox for event handling. Start the animation thread.
     * @param c 
     */
    public SwingGui(City city, Map map, Mode mode)
    {
        super();
        this.city = city;
        
        String tileset;
        CoordinateSystem coords;
        
        if (mode == Mode.ISOMETRIC) {
            coords = new Isometric(map.getXSize(),map.getYSize());
            tileset = "isometric";
        } else {
            coords = new TopDown();
            tileset = "topdown";
        }
        
        this.canvas = new Viewport(map, new ImageRepository(tileset), coords);
        this.tools = new Toolbox(map, coords);
        
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
        String d = DateFormat.getDateInstance().format(city.getDate().getTime());
        this.pop.setText("Population: "+city.population() + "  Date: "+d);
    }
}
