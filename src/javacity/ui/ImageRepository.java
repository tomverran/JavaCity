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
     * Initialise this imagerepository.
     * The code sucks, as this is testing.
     */
    public ImageRepository()
    {
        buildings = new HashMap<Integer, Image>();
        try {
            for (int id = 1; id <= 15; id++) {
                buildings.put(id, ImageIO.read(new File("images/buildings/building_"+id+".png")));
            }            
        } catch (Exception e) {
            
        }
        
        tiles = new EnumMap<Type, Image>(Type.class);
        try {
            for (Type t : Type.values()) {
                String filename = t.toString().toLowerCase();
                tiles.put(t, ImageIO.read(new File("images/tiles/"+filename+".png")));            
            }            
        } catch (Exception e) {
            
        }      
    }

    public Image getImageFor(Building b)
    {
        return this.buildings.get(b.getId());
    }
    
    public Image getImageFor(Tile t)
    {

        return this.tiles.get(t.getType());
    }
    
}
