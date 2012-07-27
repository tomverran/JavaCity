package javacity.ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javacity.lib.Point;
import javacity.ui.coordinates.CoordinateSystem;
import javacity.world.Map;
import javacity.world.Type;
import javacity.world.Category;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A class to handle GUI events and draw buttons
 * @author Tom
 */
public class SwingToolbox extends JPanel implements MouseListener 
{
    private Map city;
    private Type type;
    private Category cat;
    private int dragX, dragY;
    private CoordinateSystem coords;
    
    /**
     * Construct our SwingToolbox
     * @param city
     * @param coords 
     */
    public SwingToolbox(Map city, CoordinateSystem coords)
    {
        super();
        this.city = city;
        this.coords = coords;
        this.type = Type.RESIDENTIAL;        
        this.setLayout(new GridLayout(Type.values().length,0));
        
        int i = 0;
        for (final Category z : Category.values()) {
            JButton button = new JButton(z.toString().toLowerCase());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cat = z;
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
    
    /**
     * Handle the mouse being released - populate all tiles
     * between the mouse start & end with the requested type
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        Point startPos = coords.screenToTile(new Point(dragX, dragY));
        Point endPos = coords.screenToTile(new Point(e.getX(), e.getY()));
        
        int startx = Math.min(startPos.getX(), endPos.getX());
        int endx = Math.max(startPos.getX(), endPos.getX());

        int starty = Math.min(startPos.getY(), endPos.getY());
        int endy = Math.max(startPos.getY(), endPos.getY());
        
        for (int x = startx; x <= endx; x++) {
            for (int y = starty; y <= endy; y++) {
                if (this.city.isValidLocation(x, y)) {
                    this.city.getByLocation(x, y).setType(this.type);                    
                }
            }
        }
        
    }
    
    /**
     * Handle the mouse being pressed - save our drag start point
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        this.dragX = e.getX();
        this.dragY = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }
}
