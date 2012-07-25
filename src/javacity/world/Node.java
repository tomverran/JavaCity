/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacity.world;

/**
 *
 * @author Tom
 */
public class Node implements Comparable {
    
    private Tile tile;
    private Node parent;
    private int cost;
    
    public Node(Tile tile, Node parent, int cost)
    {
        this.tile = tile;
        this.parent = parent;
        this.cost = cost;
    }
    
    public Node(Tile tile)
    {
        this.tile = tile;
        this.parent = null;
        this.cost = 0;
    }
    
    @Override
    public int compareTo(Object o)
    {
        Node n = (Node)o;
        if (n.cost > this.cost) {
            return -1;
        } else if (n.cost < this.cost) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public boolean hasParent()
    {
        return this.parent != null;
    }
    
    public Tile tile()
    {
        return this.tile;
    }
    
    public Node parent()
    {
        return this.parent;
    }
    
    public int cost()
    {
        return this.cost;
    }
    
    @Override
    public boolean equals(Object o)
    {
        boolean eq = super.equals(o);
        if (eq) {
            return true;
        } else if (o instanceof Node) {
            Node node = (Node)o;
            return node.tile().equals(this.tile);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.tile != null ? this.tile.hashCode() : 0);
        return hash;
    }
}
