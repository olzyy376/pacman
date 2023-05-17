import bagel.Image;
import java.util.Random;

public class PinkGhost extends Ghost{
    private final static Image PINK_GHOST = new Image("res/ghostPink.png");
    private final static double speed = 3;
    private final static int RANDOM_BOUND = 4;
    private final static Random random = new Random();

    public PinkGhost(int initialX, int initialY) {
        super(initialX, initialY, PINK_GHOST, speed, LEFT);
    }

    @Override
    public void changeOrient() {
        int randomOrient = random.nextInt(RANDOM_BOUND);
        while (randomOrient == getOrient()){
            randomOrient = random.nextInt(RANDOM_BOUND);
        }
        setOrient(randomOrient);
    }
}
