import bagel.Image;

public class Cherry extends Entity implements CanBeEaten{

    private final static Image CHERRY = new Image("res/cherry.png");
    public final static int POINTS = 20;


    public Cherry(int initialX, int initialY){
        super(initialX, initialY, CHERRY);
    }

    @Override
    public int getScore() {
        return POINTS;
    }
}
