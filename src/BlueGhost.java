import bagel.Image;

public class BlueGhost extends Ghost{
    private final static Image BLUE_GHOST = new Image("res/ghostBlue.png");
    private final static int ORIGINAL_ORIENT = DOWN;
    private final static double speed = 2;
    public BlueGhost(int initialX, int initialY) {
        super(initialX, initialY, BLUE_GHOST, speed, ORIGINAL_ORIENT);
    }
}
