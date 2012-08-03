package javacity;
import java.util.ArrayList;
import javacity.lib.Component;

/**
 *
 * @author Tom
 */
public class Simulation implements Runnable 
{
    private ArrayList<Component> components;
    
    /**
     * Construct our simulation class.
     */
    public Simulation()
    {
        this.components = new ArrayList<Component>();
    }
    
    /**
     * Add a component
     * @param c 
     */
    public void addComponent(Component c)
    {
        this.components.add(c);
    }
    
    /**
     * Run this thread,
     * updating the simulation each second
     */
    @Override
    public void run()
    {
        while (true) {       
            for (Component component : components) {
                component.tick();
            }        
            try {
                Thread.sleep(1000);                    
            } catch (InterruptedException e) {
            }
        }
    }   
}
