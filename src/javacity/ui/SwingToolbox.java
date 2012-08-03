package javacity.ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.EnumMap;
import javacity.lib.Point;
import javacity.ui.coordinates.CoordinateSystem;
import javacity.world.Map;
import javacity.world.Type;
import javacity.world.Building;
import javacity.world.Category;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A class to handle GUI events and draw buttons
 * @author Tom
 */
public class SwingToolbox extends JPanel implements MouseMotionListener, MouseListener
{
    private Map city;
    private Type type;
    private Category cat;
    private CoordinateSystem coords;
    private EnumMap<Category, JPanel> subCats;
    private int[] cursorData;
    
    /**
     * Construct our SwingToolbox
     * @param city
     * @param coords 
     */
    public SwingToolbox(Map city, CoordinateSystem coords, int[] cursorData)
    {
        super();
        this.city = city;
        this.coords = coords;
        this.cursorData = cursorData;
        
        this.type = Type.RESIDENTIAL;
        this.cat = Category.ZONE;
        this.subCats = new EnumMap<Category, JPanel>(Category.class);
        
        this.setLayout(new GridLayout(Type.values().length,0));
        
        int i = 0;
        for (final Category z : Category.values()) {
            JButton button = new JButton(z.toString().toLowerCase());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    subCats.get(cat).setVisible(false);
                    cat = z;
                    subCats.get(cat).setVisible(true);                   
                }
            });
            JPanel subCat = new JPanel();
            
            subCat.setLayout(new GridLayout(z.getCount(), 0));
            for(final Type t: z.getTypes()) {
                JButton innerB = new JButton(t.name());
                innerB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        type = t;
                    }
                });
                subCat.add(innerB);
            }
            this.add(button, 0, i++);
            subCats.put(z, subCat);
            this.add(subCat, 0, i++);
            subCat.setVisible(false);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {}
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.cursorData[0] != -1) {
            this.cursorData[2] = e.getX();
            this.cursorData[3] = e.getY();
            if(!this.type.getCategory().isDraggable()) {
                this.cursorData[0] = this.cursorData[2];
                this.cursorData[1] = this.cursorData[3];
            }
        }
    }
    
    /**
     * Handle the mouse being released - populate all tiles
     * between the mouse start & end with the requested type
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Point startPos = coords.screenToTile(new Point(this.cursorData[0], this.cursorData[1]));
        Point endPos = coords.screenToTile(new Point(e.getX(), e.getY()));
        
        int startx = Math.min(startPos.getX(), endPos.getX());
        int endx = Math.max(startPos.getX(), endPos.getX());

        int starty = Math.min(startPos.getY(), endPos.getY());
        int endy = Math.max(startPos.getY(), endPos.getY());
        
        for (int x = startx; x <= endx; x++) {
            for (int y = starty; y <= endy; y++) {
                if (this.city.isValidLocation(x, y)) {
                    this.city.getByLocation(x, y).setBuilding(new Building(this.type));                    
                }
            }
        }
        
        this.cursorData[0] = -1;
        this.cursorData[1] = -1;
        this.cursorData[2] = -1;
        this.cursorData[3] = -1;
    }
    
    /**
     * Handle the mouse being pressed - save our drag start point
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.cursorData[0] = e.getX();
        this.cursorData[1] = e.getY();
        this.cursorData[2] = e.getX();
        this.cursorData[3] = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    public Type getGhostType() {
        return this.type;
    }
}
