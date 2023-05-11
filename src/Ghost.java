import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Ghost extends MovableEntity{
    public final static int RIGHT = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int UP = 3;
    private final static Image GHOST = new Image("res/ghostRed.png");

    private int orient;


    public Ghost(int initialX, int initialY, Image image, double speed) {
        super(initialX, initialY, image, speed);
    }
}