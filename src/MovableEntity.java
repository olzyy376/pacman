import bagel.Image;

abstract public class MovableEntity extends Entity{
    private double speed;


    public MovableEntity(int initialX, int initialY, Image image, double speed) {
        super(initialX, initialY, image);
        this.speed = speed;
    }
}
