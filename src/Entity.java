import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

abstract public class Entity {
    private Image image;
    private Point position;

    private boolean isActive;

    public Entity(double initialX, double initialY, Image image){
        this.position = new Point(initialX, initialY);
        this.image = image;
        this.isActive = true;
    }
    public Rectangle getBoundingBox(){
        return new Rectangle(position, image.getWidth(), image.getHeight());
    }

    public void update() {
        if (isActive) {
            image.drawFromTopLeft(position.x, position.y);
        }
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
