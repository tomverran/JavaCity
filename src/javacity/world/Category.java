/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.world;
import java.util.EnumMap;
import java.util.Set;
/**
 *
 * @author Ali
 */
public enum Category {
    BASIC(new Type[]{Type.ROAD},
        new String[]{"Road"}, false, true),
    ZONE(new Type[]{ Type.RESIDENTIAL,  Type.COMMERCIAL,    Type.INDUSTRIAL},
        new String[]{"Residential",     "Commercial",       "Industrial"}, true, true),
    CIVIC(new Type[]{Type.POLICE,       Type.HOSPITAL,      Type.FIREHOUSE},
        new String[]{"Police Station",  "Hospital",         "Firehouse"}, false, false);
    
    private EnumMap<Type, String> types;
    private boolean evolving;
    private boolean draggable;
    
    private Category(Type types[], String names[], boolean evolve, boolean draggable) {
        this.evolving = evolve;
        this.draggable = draggable;
        this.types = new EnumMap<>(Type.class); 
        for(int i = 0; i < types.length; i++) {
            this.types.put(types[i], names[i]);
            types[i].setCategory(this);
        }
    }
    
    public Set<Type> getTypes() {
        return types.keySet();
    }
    
    public int getCount() {
        return types.size();
    }
    
    public boolean isEvolving() {
        return this.evolving;
    }
    
    public boolean isDraggable() {
        return this.draggable;
    }
}
