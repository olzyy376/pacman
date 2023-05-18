import bagel.Image;

/**
 * this is a class used to present ghosts that has a pink colour in the game
 */
public class PinkGhost extends Ghost{
    private final static Image PINK_GHOST = new Image("res/ghostPink.png");
    private final static double speed = 3;
    /**
     * A constructor used to set up the position, image, speed and direction of the pink ghost
     */
    public PinkGhost(int initialX, int initialY) {
        super(initialX, initialY, PINK_GHOST, speed, LEFT, true);
    }
}
