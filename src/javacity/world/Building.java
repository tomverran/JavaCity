package javacity.world;

/**
 * A simple container class
 * for buildings within the city
 * @author Tom
 */
public class Building {
    
    private int id;
    private String name;
    private int wealth;
    private Type type;
    private boolean occupied;
    private int level;
    
    public Building(Type t) {
        this.type = t;
        this.level = 0;
    }

    public int getLevel() {
        return this.level;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the wealth
     */
    public int getWealth() {
        return wealth;
    }

    /**
     * @param wealth the wealth to set
     */
    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    public boolean isOccupied()
    {
        return this.occupied;
    }
    
    public void setOccupied(boolean occupied)
    {
        this.occupied = occupied;
    }
    
    
}
