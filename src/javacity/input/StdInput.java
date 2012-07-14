package javacity.input;
import javacity.Component;
import javacity.world.City;
import java.util.Scanner;
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
        while (s.hasNext()) {
            String input = s.next();
            if (input.equals("continue")) {
                break;
            } else if (input.equals("exit")) {
                System.exit(0); //I'm so graceful
            }
        }
    }   
}

