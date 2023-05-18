import bagel.Image;
/**
 * this is a class used to present ghosts that has a red colour in the game
 */
public class RedGhost extends Ghost{
    private final static Image RED_GHOST = new Image("res/ghostRed.png");
    private final static int ORIGINAL_ORIENT = RIGHT;

    /**
     * A constructor used to set up the position, image and direction of the red ghost
     */
    public RedGhost(int initialX, int initialY, double speed) {
        super(initialX, initialY, RED_GHOST, speed, ORIGINAL_ORIENT, false);
    }
}
