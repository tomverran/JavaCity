package javacity.ui;
import java.awt.BorderLayout;
import javacity.world.City;
import javax.swing.JFrame;

/**
 * A prettier GUI
 * @author Tom
 */
public class Gui extends JFrame {
    
    private GuiCanvas canvas;
    private GuiToolbox tools;
    
    public Gui(City c)
    {
        super();
        this.setVisible(true);
        this.canvas = new GuiCanvas(c);
        this.tools = new GuiToolbox(c);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(this.tools,BorderLayout.WEST);
        
        this.add(this.canvas,BorderLayout.CENTER);
        this.canvas.addMouseListener(this.tools);
        this.canvas.init();
        this.pack();

        this.setLocationRelativeTo(null);
        this.setTitle("JavaCity");
    }
    
    public void updateCanvas()
    {
        this.canvas.draw();
    }
    
    public void repaint()
    {
        super.repaint();
        this.canvas.draw();
    }
    
}
