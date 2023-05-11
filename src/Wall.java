import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Wall extends Entity {
    private final static Image WALL = new Image("res/wall.png");

    public Wall(int initialX, int initialY) {
        super(initialX, initialY, WALL);
    }
}
