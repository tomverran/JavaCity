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
    private EnumMap<Type, Image[]> evolvingImages;
    
    /**
     * Initialise this object.
     * The code sucks, as this is testing.
     * @param set the icon set to use
     */
    public ImageRepository(String set)
    {
        typeImages = new EnumMap<Type, Image>(Type.class);
        evolvingImages = new EnumMap<Type, Image[]>(Type.class);
        try {
            for(Category c: Category.values()) {
                String cat = c.toString();
                if(c.isEvolving()) {
                    for(Type t: c.getTypes()) {
                        Image[] img = new Image[4];
                        for(int i=0; i<4; i++) {
                            img[i] = ImageIO.read(new File("images/"+set+"/"+cat+"/"+t.toString()+"_"+i+".png"));
                        }
                        evolvingImages.put(t, img);
                    }
                } else {
                    for(Type t: c.getTypes()) {
                        typeImages.put(t, ImageIO.read(new File("images/"+set+"/"+cat+"/"+t.toString()+".png")));
                    }
                }
            } 
        } catch(Exception e) {
            
        }
        
        terrainImages = new EnumMap<Terrain, Image>(Terrain.class);
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
    public Image getImageFor(Type t) {
        if(t.getCategory().isEvolving()) {
            return this.evolvingImages.get(t)[0];
        } else {
            return this.typeImages.get(t);
        }
    }
    public Image getImageFor(Building b) {
        if(b.getType().getCategory().isEvolving()) {
            return this.evolvingImages.get(b.getType())[b.getLevel()];
        } else {
            return this.typeImages.get(b.getType());
        }
    }
}
