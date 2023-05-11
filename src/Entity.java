import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

abstract public class Entity {
    private Image image;
    private Point position;

    public Entity(int initialX, int initialY, Image image){
        this.position = new Point(initialX, initialY);
        this.image = image;
    }
    public Rectangle getBoundingBox(){
        return new Rectangle(position, image.getWidth(), image.getHeight());
    }

    public void update() {
        image.drawFromTopLeft(position.x, position.y);
    }

}
