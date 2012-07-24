package javacity.world;

import java.util.Random;

/**
 * A class that acts as a repository of buildings, from where dwellings
 * great and small may be plucked by the City and set down to enrich their
 * surroundings, and residents.
 * @author Tom
 */
public class BuildingRepository 
{   
    private Building[] residential;
    private Building[] commercial;
    private Building[] industrial;
    private static Random r;
    static {
        r = new Random();
    }
    
    public BuildingRepository()
    {
        residential = new Building[5];
        commercial = new Building[5];
        industrial = new Building[5];
        
        String[] rNames = new String[]{"Zeke's House",
                                       "Java Hut","Small Bungalow",
                                       "Metro Land Villa",
                                       "LLama Towers"};
        
        String[] cNames = new String[]{"BarStrucks",
                                       "Wernham Hogg","Spanish Flea Market",
                                       "Sparkles Mall",
                                       "Orange Store"};        
        
        String[] iNames = new String[]{"Smoke Incorporated",
                                       "Trouble at t'Mill","Renholm Industries",
                                       "Pop Culture Reference Ltd",
                                       "Evil Co"};
        
        int id = 0;
        for (int i = 0; i < iNames.length; i++) {
            Building b = new Building();
            b.setType(Type.INDUSTRIAL);
            b.setName(iNames[i]);
            industrial[i] = b;
            id += 1;
            b.setId(id);
        }
        
        for (int i=0; i < cNames.length; i++) {
            Building b = new Building();
            b.setType(Type.COMMERCIAL);
            b.setName(cNames[i]);
            commercial[i] = b;
            id += 1;
            b.setId(id);
        }        
        
        for (int i = 0; i < rNames.length; i++) {
            Building b = new Building();
            b.setType(Type.RESIDENTIAL);
            b.setName(rNames[i]);
            residential[i] = b;
            id += 1;
            b.setId(id);
        }                
    }
    
    /**
     * Get a random building
     * @param type
     * @return 
     */
    public Building getRandomBuilding(Type type)
    {
        if (type == Type.INDUSTRIAL) {
            return industrial[r.nextInt(industrial.length)];
        } else if (type == Type.COMMERCIAL) {
            return commercial[r.nextInt(commercial.length)];
        } else if (type == Type.RESIDENTIAL) {
            return residential[r.nextInt(residential.length)];
        } else {
            throw new IllegalArgumentException("Unsupported Type");
        }
    }
}
