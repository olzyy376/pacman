import bagel.*;
import bagel.util.Point;

public class Entity {
    private Image image;
    private Point position;

    public Entity(int initialX, int initialY){
        this.position = new Point(initialX, initialY);
    }
}
