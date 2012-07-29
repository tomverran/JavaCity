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
        new String[]{"Road"}, false),
    ZONE(new Type[]{ Type.RESIDENTIAL,  Type.COMMERCIAL,    Type.INDUSTRIAL},
        new String[]{"Residential",     "Commercial",       "Industrial"}, true),
    CIVIC(new Type[]{Type.POLICE,       Type.HOSPITAL,      Type.FIREHOUSE},
        new String[]{"Police Station",  "Hospital",         "Firehouse"}, false);
    
    private EnumMap<Type, String> types;
    private boolean evolving;
    
    private Category(Type types[], String names[], boolean evolve) {
        this.evolving = evolve;
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
}
