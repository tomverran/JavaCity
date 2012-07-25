package javacity.ui;
import java.awt.Image;
import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import javacity.world.Building;
import javacity.world.Tile;
import javacity.world.Type;
import javax.imageio.ImageIO;

/**
 * A repo of images
 * @author Tom
 */
public class ImageRepository {
    
    private HashMap<Integer, Image> buildings;
    private EnumMap<Type, Image> tiles;
    
    /**
     * Initialise this object.
     * The code sucks, as this is testing.
     * @param set the icon set to use
     */
    public ImageRepository(String set)
    {
        buildings = new HashMap<Integer, Image>();
        try {
            for (int id = 1; id <= 16; id++) {
                buildings.put(id, ImageIO.read(new File("images/"+set+"/buildings/building_"+id+".png")));
            }            
        } catch (Exception e) {
            
        }
        
        tiles = new EnumMap<Type, Image>(Type.class);
        try {
            for (Type t : Type.values()) {
                String filename = t.toString().toLowerCase();
                tiles.put(t, ImageIO.read(new File("images/"+set+"/tiles/"+filename+".png")));            
            }            
        } catch (Exception e) {
            
        }      
    }

    /**
     * Get the image for a particular building
     * @param b
     * @return 
     */
    public Image getImageFor(Building b)
    {
        return this.buildings.get(b.getId());
    }
    
    /**
     * Get the image for a particular tile.
     * @param t
     * @return 
     */
    public Image getImageFor(Tile t)
    {
        return this.tiles.get(t.getType());
    }
}
