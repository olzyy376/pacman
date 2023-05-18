import bagel.Image;

/**
 * this is a class used to present dots in the game
 */
public class Dot extends Entity implements CanBeEaten{
    private final static Image DOT = new Image("res/dot.png");
    private final static int POINTS = 10;

    /**
     * A constructor used to set up the position and image of dots
     */
    public Dot(int initialX, int initialY){
        super(initialX, initialY, DOT);
    }


    /**
     * method that is used to get the score of dot
     */
    @Override
    public int getScore() {
        return POINTS;
    }
}

