package javacity.ui;
import java.awt.Image;
import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import javacity.world.Building;
import javacity.world.Tile;
import javacity.world.Category;
import javacity.world.Type;
import javacity.world.Terrain;
import javax.imageio.ImageIO;

/**
 * A repo of images
 * @author Tom
 */
public class ImageRepository {
    
    private HashMap<Integer, Image> buildings;
    private EnumMap<Type, Image> typeImages;
    private EnumMap<Terrain, Image> terrainImages;
    
    /**
     * Initialise this object.
     * The code sucks, as this is testing.
     * @param set the icon set to use
     */
    public ImageRepository(String set)
    {
        /*for(Category c: Category.values()) {
            String cat = c.toString();
            for(Type t: c.getTypes()) {
                tiles.put(t, ImageIO.read(new File("images/"+set+"/"+cat+"/"+t.toString()+".png")));
            }
        }*/
        
        
        buildings = new HashMap<>();
        try {
            for (int id = 1; id <= 16; id++) {
                buildings.put(id, ImageIO.read(new File("images/"+set+"/buildings/building_"+id+".png")));
            }            
        } catch (Exception e) {
            
        }
        
        typeImages = new EnumMap<>(Type.class);
        try {
            for (Type t : Category.BASIC.getTypes()) {
                String filename = t.toString().toLowerCase();
                typeImages.put(t, ImageIO.read(new File("images/"+set+"/tiles/"+filename+".png")));            
            }  
            for (Type t : Category.ZONE.getTypes()) {
                String filename = t.toString().toLowerCase();
                typeImages.put(t, ImageIO.read(new File("images/"+set+"/tiles/"+filename+".png")));            
            }
        } catch (Exception e) {
            
        }  
        
        terrainImages = new EnumMap<>(Terrain.class);
        try {
            for(Terrain t: Terrain.values()) {
                terrainImages.put(t, ImageIO.read(new File("images/"+set+"/terrain/"+t.toString().toLowerCase()+".png")));
            }
        } catch (Exception e) {
            
        }
    }
    
    /**
     * Get the image for a particular tile.
     * @param t
     * @return 
     */
    public Image getImageFor(Tile t) {
        return this.terrainImages.get(t.getTerrain());
    }
    public Image getImageFor(Building b) {
        return this.typeImages.get(b.getType());
    }
}
