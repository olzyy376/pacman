import bagel.Image;
import java.util.Random;

public class PinkGhost extends Ghost{
    private final static Image PINK_GHOST = new Image("res/ghostPink.png");
    private final static double speed = 3;
    public PinkGhost(int initialX, int initialY) {
        super(initialX, initialY, PINK_GHOST, speed, LEFT, true);
    }
}
