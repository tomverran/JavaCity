/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javacity.lib.Component;
import javacity.ui.UserInterface;
import javacity.world.City;

/**
 *
 * @author Tom
 */
public class Time implements Component {
    
    private City c;
    private UserInterface iface;
    private static final int DAYS_PER_TICK = 10;
    
    public Time(City c, UserInterface iface)
    {
        this.c = c;
        this.iface = iface;
    }
    
    public void tick()
    {
        GregorianCalendar date = this.c.getDate();
        int oldYear = date.get(GregorianCalendar.YEAR);
        
        date.add(Calendar.DAY_OF_YEAR, this.DAYS_PER_TICK);
        int newYear = date.get(GregorianCalendar.YEAR);
        
        if (oldYear != newYear && newYear % 10 == 0) {
            this.iface.displayMessage("A new decade has started!", UserInterface.MessageType.GOOD);
        }
        
    }
    
}
