import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Dot extends CanBeEaten{
    private final static Image DOT = new Image("res/dot.png");
    public final static int POINTS = 10;


    public Dot(int initialX, int initialY){
        super(initialX, initialY, DOT, POINTS);
    }

}

