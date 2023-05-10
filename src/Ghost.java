import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Ghost {
    private final Image GHOST = new Image("res/ghostRed.png");
    private final Point position;

    public Ghost(int initialX, int initialY){
        this.position = new Point(initialX, initialY);
    }

    /**
     * Method that performs state update
     */
    public void update() {
        GHOST.drawFromTopLeft(position.x, position.y);
    }

    public Rectangle getBoundingBox(){
        return new Rectangle(position, GHOST.getWidth(), GHOST.getHeight());
    }

}