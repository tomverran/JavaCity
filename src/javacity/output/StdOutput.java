package javacity.output;
import javacity.world.City;
import java.util.HashMap;
import javacity.Component;

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
        this.gfx.put("grass", "~");
        this.gfx.put("commercial", "c");
        this.gfx.put("road", "#");
    }
    
    /**
     * Render a city to Stdout.
     */
    @Override
    public void tick()
    {
        City c = this.getCity();
        for(int y = 0; y < c.getSize(); y++) {
            for (int x = 0; x < c.getSize(); x++) {
                System.out.print(this.gfx.get(c.getByLocation(x, y).getType()));
            }
            System.out.println();
        }
    }
    
}
