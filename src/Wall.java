import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Wall {
    private final Image WALL = new Image("res/wall.png");
    private final Point position;

    public Wall(int initialX, int initialY){
        this.position = new Point(initialX, initialY);
    }

    /**
     * Method that performs state update
     */
    public void update() {
        WALL.drawFromTopLeft(this.position.x, this.position.y);
    }

    public Rectangle getBoundingBox(){
        return new Rectangle(position, WALL.getWidth(), WALL.getHeight());
    }
}
