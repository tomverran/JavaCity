package javacity;
import javacity.world.City;
import javacity.output.StdOutput;
import javacity.input.StdInput;
import javacity.world.Tile;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class JavaCity 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        City city = new City();
        Tile tile = city.getByLocation(2, 2);
        tile.setType("commercial");
        

        
        for (Tile t2 : city.getNeighbours(tile)) {
            t2.setType("road");
        }
        
        ArrayList<Component> components = new ArrayList<Component>();
        components.add(new StdInput(city));
        components.add(new StdOutput(city));
        
        while (true) {
            for (Component component : components) {
                component.tick();
            }
        }
    }
}
