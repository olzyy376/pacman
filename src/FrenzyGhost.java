import bagel.Image;

/**
 * this is a class used to present ghosts that has a pink colour in the game
 */
public class FrenzyGhost extends Ghost implements CanBeEaten{

    private final static Image FRENZY_GHOST = new Image("res/ghostFrenzy.png");

    private final static double DECREASED_FRENZY_SPEED = 0.5;

    private final static int POINTS = 30;
    /**
     * A constructor used to set up the position, image, speed and direction of pellets
     */
    public FrenzyGhost(Ghost ghost, boolean isRandomMoving) {
        super(ghost.getPosition().x, ghost.getPosition().y, FRENZY_GHOST,
                ghost.getSpeed() - DECREASED_FRENZY_SPEED, ghost.getOrient(), isRandomMoving);
    }
    /**
     * method that is used to get the score of frenzy ghosts
     */
    @Override
    public int getScore() {
        return POINTS;
    }
}
