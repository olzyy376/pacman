import bagel.Image;
/**
 * this is a class used to present cherries in the game
 */
public class Cherry extends Entity implements CanBeEaten{

    private final static Image CHERRY = new Image("res/cherry.png");
    private final static int POINTS = 20;

    /**
     * A constructors used to set up the position and image of cherries
     */
    public Cherry(int initialX, int initialY){
        super(initialX, initialY, CHERRY);
    }

    /**
     * method that is used to get the score of cherry
     */
    @Override
    public int getScore() {
        return POINTS;
    }
}
