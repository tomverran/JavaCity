/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.game.component;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javacity.lib.Component;
import javacity.world.City;

/**
 *
 * @author Tom
 */
public class Time implements Component {
    
    private City c;
    private static final int DAYS_PER_TICK = 10;
    
    public Time(City c)
    {
        this.c = c;
    }
    
    public void tick()
    {
        GregorianCalendar date = this.c.getDate();
        date.add(Calendar.DAY_OF_YEAR, this.DAYS_PER_TICK);
    }
    
}