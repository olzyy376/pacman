import bagel.Image;
import bagel.util.Point;

import java.util.Random;

/**
 * this is an abstract class used to present ghosts in the game
 */
abstract public class Ghost extends Entity implements Movable{

    private final static int ORIENT_BOUND = 4;
    private final static Random random = new Random();
    private int orient;
    private final double speed;
    private Point prevPosition;
    private final Point originalPosition;
    private final boolean isRandomMove;
    /**
     * A constructor used to set up the position, image, speed, direction and original position of each entity
     */
    public Ghost(double initialX, double initialY, Image image, double speed, int orient, boolean isRandomMove) {
        super(initialX, initialY, image);
        this.speed = speed;
        this.orient = orient;
        this.originalPosition = new Point(initialX, initialY);
        this.isRandomMove = isRandomMove;
    }
    /**
     * A method used to allow ghosts in level 1 to move
     */
    @Override
    public void move(double xMove, double yMove){
        prevPosition = getPosition();
        double x = getPosition().x;
        double y = getPosition().y;
        Point newPoint = new Point(x + xMove, y + yMove);
        setPosition(newPoint);
    }

    /**
     * A method used to update the position of the ghosts based on their current position and orientation
     */
    @Override
    public void update() {
        if (orient == UP){
            move(0, -speed);
        }
        else if (orient == RIGHT){
            move(speed, 0);
        }
        else if (orient == DOWN){
            move(0,speed);
        }
        else if (orient == LEFT) {
            move(-speed,0);
        }
        super.update();
    }
    /**
     * A method that allows the ghosts to move back to its previous position when hitting the wall
     */
    @Override
    public void moveBack(){
        setPosition(prevPosition);
    }

    /**
     * A method that allows the ghosts to change their orientation when hitting the wall
     */
    public void changeOrient(){
        if (isRandomMove){
            int randomOrient = random.nextInt(ORIENT_BOUND);
            while (randomOrient == getOrient()){
                randomOrient = random.nextInt(ORIENT_BOUND);
            }
            orient = randomOrient;
        }
        else {
            if (orient % 2 == 1) {
                orient = ORIENT_BOUND - orient;
            } else {
                orient = (ORIENT_BOUND / 2) - orient;
            }
        }
    }

    /**
     * A method used to get the orientation of the ghosts
     */
    public int getOrient() {
        return orient;
    }

    /**
     * A method used to get the speed of the ghosts
     */
    @Override
    public double getSpeed() {
        return speed;
    }
    /**
     * A method used to reset the ghosts to its starting position
     */
    @Override
    public void resetPosition(){
        setPosition(originalPosition);
    }
}