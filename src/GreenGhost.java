import bagel.Image;
import java.util.Random;
/**
 * this is a class used to present ghosts that has a green colour in the game
 */
public class GreenGhost extends Ghost{

    private final static Image GREEN_GHOST = new Image("res/ghostGreen.png");
    private final static double speed = 4;
    private final static int RANDOM_BOUND = 2;
    private final static Random random = new Random();
    /**
     * A constructor used to set up the position, image, speed and direction of the green ghost
     */
    public GreenGhost(int initialX, int initialY) {
        super(initialX, initialY, GREEN_GHOST, speed, random.nextInt(RANDOM_BOUND), false);
    }
}
