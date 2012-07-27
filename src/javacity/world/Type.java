package javacity.world;

import java.util.EnumMap;

/**
 * A type enum for city structures.
 * Left outside of any particular class
 * @author Tom
 */

public enum Type {
    GRASS, ROAD,
    RESIDENTIAL, COMMERCIAL, INDUSTRIAL,
    POLICE, HOSPITAL, FIREHOUSE,
    POWER;
    
    private Category cat;
    
    public void setCategory(Category c) {
        this.cat = c;
    }
}




/*
public class TileType {
    public enum Basic implements Type {
        GRASS, ROAD
    }
    public enum Zone implements Type{
        RESIDENTIAL, COMMERCIAL, INDUSTRIAL
    }
    public enum Civic implements Type{
        POLICE, HOSPITAL, FIREHOUSE
    }
    public enum Energy implements Type {
        POWER
    }
    
    public static EnumMap<Type, String> types;
}*/
