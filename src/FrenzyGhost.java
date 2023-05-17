import bagel.Image;

public class FrenzyGhost extends Ghost implements CanBeEaten{

    private final static Image FRENZY_GHOST = new Image("res/ghostFrenzy.png");

    private final static double DECREASED_FRENZY_SPEED = 0.5;

    private final static int POINTS = 30;

    public FrenzyGhost(Ghost ghost) {
        super(ghost.getPosition().x, ghost.getPosition().y, FRENZY_GHOST,
                ghost.getSpeed() - DECREASED_FRENZY_SPEED, ghost.getOrient());
    }

    @Override
    public int getScore() {
        return POINTS;
    }
}
