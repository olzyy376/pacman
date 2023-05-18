import bagel.Image;
/**
 * this is a class used to present cherries in the game
 */
public class Pellet extends Entity implements CanBeEaten{

    private final static Image PELLET = new Image("res/pellet.png");
    private final static int POINTS = 0;
    /**
     * A constructor used to set up the position and image of pellets
     */
    public Pellet(int initialX, int initialY){
        super(initialX, initialY, PELLET);
    }

    /**
     * method that is used to get the score of pellet
     */
    @Override
    public int getScore() {
        return POINTS;
    }
}
