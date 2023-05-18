import bagel.Image;
/**
 * this is a class used to present ghosts that has a blue colour in the game
 */
public class BlueGhost extends Ghost{
    private final static Image BLUE_GHOST = new Image("res/ghostBlue.png");
    private final static int ORIGINAL_ORIENT = DOWN;
    private final static double speed = 2;
    /**
     * A constructor used to set up the position, image, speed and direction of the blue ghost
     */
    public BlueGhost(int initialX, int initialY) {
        super(initialX, initialY, BLUE_GHOST, speed, ORIGINAL_ORIENT, false);
    }
}
