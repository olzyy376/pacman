import bagel.Image;
import bagel.util.Point;

import java.util.Random;


abstract public class Ghost extends Entity implements Movable{

    private final static int ORIENT_BOUND = 4;
    private final static Random random = new Random();
    private int orient;
    private double speed;
    private Point prevPosition;
    private Point originalPosition;
    private final boolean isRandomMove;

    public Ghost(double initialX, double initialY, Image image, double speed, int orient, boolean isRandomMove) {
        super(initialX, initialY, image);
        this.speed = speed;
        this.orient = orient;
        this.originalPosition = new Point(initialX, initialY);
        this.isRandomMove = isRandomMove;
    }
    public void move(double xMove, double yMove){
        prevPosition = getPosition();
        double x = getPosition().x;
        double y = getPosition().y;
        Point newPoint = new Point(x + xMove, y + yMove);
        setPosition(newPoint);
    }

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

    public void moveBack(){
        setPosition(prevPosition);
    }

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

    public int getOrient() {
        return orient;
    }

    public double getSpeed() {
        return speed;
    }
    public void resetPosition(){
        setPosition(originalPosition);
    }
}