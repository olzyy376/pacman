import bagel.Image;
import bagel.util.Point;


abstract public class Ghost extends Entity implements Movable{
    private int orient;
    private double speed;
    private Point prevPosition;
    private Point originalPosition;

    public Ghost(double initialX, double initialY, Image image, double speed, int orient) {
        super(initialX, initialY, image);
        this.speed = speed;
        this.orient = orient;
        this.originalPosition = new Point(initialX, initialY);
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
        if (orient % 2 == 1){
            orient = 4 - orient;
        }
        else{
            orient = 2 - orient;
        }
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public double getSpeed() {
        return speed;
    }
    public void resetPosition(){
        setPosition(originalPosition);
    }
}