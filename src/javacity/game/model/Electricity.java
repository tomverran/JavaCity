package javacity.game.model;

import java.util.ArrayList;
import javacity.world.Tile;
import javacity.world.Tile.Zone;

/**
 *
 * @author Tom
 */
public class Electricity extends AbstractModel {

    @Override
    public void tick()
    {
        ArrayList<Tile> plants = AbstractModel.map.new Query()
                                 .withType(Zone.POWERPLANT)
                                 .fetchAll();
        
        for (Tile plant : plants) {
            for (Tile place : AbstractModel.map.getNeighbours(plant,3)) {
                place.addFlag(Tile.Flag.POWERED);
            }
        }
    }
}
