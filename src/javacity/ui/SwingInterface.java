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
import javax.swing.JOptionPane;

/**
 * A prettier GUI
 * @author Tom
 */
public class SwingInterface extends JFrame implements Component, UserInterface 
{    
    public enum Mode {
        ISOMETRIC, TOPDOWN
    }
    
    private SwingViewport canvas;
    private SwingToolbox tools;
    private JLabel pop;
    private City city;
    private int[] cursorData;
    
    /**
     * Initialise our GUI, assembling a Canvas for drawing
     * and a SwingToolbox for event handling. Start the animation thread.
     * @param c 
     */
    public SwingInterface(City city, Map map, Mode mode)
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
        
        cursorData = new int[]{-1, -1, -1, -1};
        
        this.canvas = new SwingViewport(map, new ImageRepository(tileset), coords, cursorData);
        this.tools = new SwingToolbox(map, coords, cursorData);
        
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
        this.canvas.addMouseMotionListener(this.tools);
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
    
    /**
     * Display a message
     * @param message
     * @param type 
     */
    @Override
    public void displayMessage(String message, MessageType type) 
    {
        JOptionPane.showMessageDialog(this, message);
    }
    
    public javacity.world.Type getGhostType() {
        return this.tools.getGhostType();
    }
}
