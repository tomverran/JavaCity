/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.world;
import java.util.EnumMap;
/**
 *
 * @author Ali
 */
public enum Category {
    BASIC(new Type[]{Type.GRASS,    Type.ROAD},
        new String[]{"Grass",       "Road"}),
    ZONE(new Type[]{ Type.RESIDENTIAL,  Type.COMMERCIAL,    Type.INDUSTRIAL},
        new String[]{"Residential",     "Commercial",       "Industrial"}),
    CIVIC(new Type[]{Type.POLICE,       Type.HOSPITAL,      Type.FIREHOUSE},
        new String[]{"Police Station",  "Hospital",         "Firehouse"});
    
    private EnumMap<Type, String> types;
    
    private Category(Type types[], String names[]) {
        this.types = new EnumMap<Type, String>(Type.class); 
        for(int i = 0; i < types.length; i++) {
            this.types.put(types[i], names[i]);
            types[i].setCategory(this);
        }
    }
}