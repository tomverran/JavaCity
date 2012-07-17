package javacity.game.component;
import java.util.ArrayList;
import java.util.Iterator;
import javacity.lib.Component;
import javacity.world.City;
import javacity.world.Tile;

/**
 * A class to handle business growth and decline,
 * based solely on the residential populace, economics confuse me.
 * @author Tom
 */
public class Workplace extends Component 
{   
    /**
     * Construct
     * @param c 
     */
    public Workplace(City c)
    {
        super(c);
    }
    
    /**
     * Tick.
     */
    @Override
    public void tick()
    {
        //how many hapless drones inhabit our city?
        int population = this.getCity().getTilesByType("occupied_r").size();
        
        //occupied workplaces
        ArrayList<Tile> occupied_i = this.getCity().getTilesByType("occupied_i");
        ArrayList<Tile> occupied_c = this.getCity().getTilesByType("occupied_c");
       
        int countCommercial = occupied_c.size();
        int countIndustry = occupied_i.size();
        
        //how many free job slots are there.
        int jobDemand = population - (countCommercial + countIndustry);

        if (jobDemand < 0) {
            
            //more jobs than people? Industries close first, then commercial.
            while (jobDemand < 0 && countIndustry > 0 && countCommercial > 0) {
                
                jobDemand++;
                if (countIndustry > 0) {
                    occupied_i.iterator().next().setType("zone_i");
                    countIndustry--;
                } else {
                    occupied_c.iterator().next().setType("zone_c");
                    countCommercial--;
                }
            }   
        
        } else {
            
            //unoccupied workplaces
            Iterator<Tile> zone_i = this.getCity().getTilesByType("zone_i").iterator();
            Iterator<Tile> zone_c = this.getCity().getTilesByType("zone_c").iterator();

            //more jobs than people? People would prefer a cushy
            //desk job over a hard factory job, but will take either
            while (jobDemand > 0 && (zone_i.hasNext() || zone_c.hasNext())) {
                
                jobDemand--;
                if (zone_c.hasNext()) {
                    zone_c.next().setType("occupied_c");
                } else {
                    zone_i.next().setType("occupied_i");
                }
            }            
        }
    }
}
