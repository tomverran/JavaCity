package javacity.world;

/**
 * A type enum for city structures.
 * Left outside of any particular class
 * @author Tom
 */

public enum Type {
    ROAD,
    RESIDENTIAL, COMMERCIAL, INDUSTRIAL,
    POLICE, HOSPITAL, FIREHOUSE,
    POWER;
    
    private Category cat;
    
    public void setCategory(Category c) {
        this.cat = c;
    }
    
    public Category getCategory() {
        return this.cat;
    }
}
