package javacity.ui;
import javacity.lib.Component;
import javacity.world.City;
import java.util.Scanner;
import javacity.world.Tile;
/**
 * A simple command line input class
 * purely for testing game logic.
 * @author Tom
 */
public class StdInput extends Component 
{   
    /**
     * construct, obv
     * @param City c 
     */
    public StdInput(City c)
    {
        super(c);
    }
    
    /**
     * Read in and react to player commands.
     */
    @Override
    public void tick()
    {
        System.out.print("Command: ");
        Scanner s = new Scanner(System.in);
        s.useDelimiter("\n");
        
        if (s.hasNext()) {
            String[] input = s.next().split(" ");
            if (input[0].equals("exit")) {
                System.exit(0); //I'm so graceful
                
            //tile placement, dodgy, for testing.
            } else if (input[0].equals("place") && input.length == 4) {
                Tile t = this.getCity().getByLocation(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                t.setType(input[1]);
            }
        }
    }   
}

