import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Dot {
    private final Image DOT = new Image("res/dot.png");
    public final static int POINTS = 10;
    private final Point position;

    private boolean isActive;

    public Dot(int initialX, int initialY){
        this.isActive = true;
        this.position = new Point(initialX, initialY);
    }

    /**
     * Method that performs state update
     */
    public void update() {
        if (isActive){
            DOT.drawFromTopLeft(position.x, position.y);
        }
    }

    public Rectangle getBoundingBox(){
        return new Rectangle(position, DOT.getWidth(), DOT.getHeight());
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}

