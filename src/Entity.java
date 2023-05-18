import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * this is an abstract class that is used to present entities (objects) in the game except for the pacman
 */
abstract public class Entity {
    private Image image;
    private Point position;

    private boolean isActive;
    /**
     * A constructor used to set up the position, image and activeness for each entity
     */
    public Entity(double initialX, double initialY, Image image){
        this.image = image;
        this.position = new Point(initialX, initialY);
        this.isActive = true;
    }
    /**
     * A method that used to create a rectangle that allows collisions between entities
     */
    public Rectangle getBoundingBox(){
        return new Rectangle(position, image.getWidth(), image.getHeight());
    }

    /**
     * A method that used to update the current conditions of each entity in the game
     */
    public void update() {
        if (isActive) {
            image.drawFromTopLeft(position.x, position.y);
        }
    }

    /**
     * A method used to get the position of the entities
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * A method used to set up the position of the entities
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    /**
     * A method used to get the activeness of the entities
     */
    public boolean isActive() {
        return isActive;
    }
    /**
     * A method used to set up the activeness of the entities
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
