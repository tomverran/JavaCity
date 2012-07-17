package javacity.ui;
import javacity.world.City;
import java.util.HashMap;
import javacity.lib.Component;

/**
 * Super simple StdOut renderer so we can vaguely see
 * what our city looks like when trying things out
 * @author Tom
 */
public class StdOutput extends Component {
    
    private HashMap<String, String> gfx;
    
    /**
     * Construct our StdOutput component
     * @param c 
     */
    public StdOutput(City c)
    {
        super(c);
        this.gfx = new HashMap<String, String>();
        
        //todo make non hardcoded.
        this.gfx.put("grass", "~");
        this.gfx.put("zone_c", "c");
        this.gfx.put("zone_r","r");
        this.gfx.put("zone_i", "i");
        
        this.gfx.put("occupied_c", "=");
        this.gfx.put("occupied_i", "|");
        this.gfx.put("occupied_r","^");
        
        this.gfx.put("hospital","H");
        this.gfx.put("road", "#");
    }
    
    /**
     * Render a city to Stdout.
     */
    @Override
    public void tick()
    {
        City c = this.getCity();
        for(int y = 0; y < c.getXSize(); y++) {
            for (int x = 0; x < c.getYSize(); x++) {
                System.out.print(this.gfx.get(c.getByLocation(x, y).getType()));
            }
            System.out.println();
        }
    }
    
}
