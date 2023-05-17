import bagel.Image;

public class RedGhost extends Ghost{
    private final static Image RED_GHOST = new Image("res/ghostRed.png");
    private final static int ORIGINAL_ORIENT = RIGHT;

    public RedGhost(int initialX, int initialY, double speed) {
        super(initialX, initialY, RED_GHOST, speed, ORIGINAL_ORIENT);
    }
}
