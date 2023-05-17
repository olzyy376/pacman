import bagel.Image;


public class Dot extends Entity implements CanBeEaten{
    private final static Image DOT = new Image("res/dot.png");
    public final static int POINTS = 10;


    public Dot(int initialX, int initialY){
        super(initialX, initialY, DOT);
    }

    @Override
    public int getScore() {
        return POINTS;
    }
}

