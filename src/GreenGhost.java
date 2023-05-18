import bagel.Image;
import java.util.Random;

public class GreenGhost extends Ghost{

    private final static Image GREEN_GHOST = new Image("res/ghostGreen.png");
    private final static double speed = 4;
    private final static int RANDOM_BOUND = 2;
    private final static Random random = new Random();

    public GreenGhost(int initialX, int initialY) {
        super(initialX, initialY, GREEN_GHOST, speed, random.nextInt(RANDOM_BOUND), false);
    }
}
