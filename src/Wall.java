import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
/**
 * Class used to present walls in the game
 */
public class Wall extends Entity {
    private final static Image WALL = new Image("res/wall.png");
    /**
     * constructors used to set up the position and image of the wall
     */
    public Wall(int initialX, int initialY) {
        super(initialX, initialY, WALL);
    }
}
