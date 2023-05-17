import bagel.Image;

public class Pellet extends Entity implements CanBeEaten{

    private final static Image PELLET = new Image("res/pellet.png");
    public final static int POINTS = 0;

    public Pellet(int initialX, int initialY){
        super(initialX, initialY, PELLET);
    }

    @Override
    public int getScore() {
        return POINTS;
    }
}
