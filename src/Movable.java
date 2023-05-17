public interface Movable {
    int RIGHT = 0;
    int DOWN = 1;
    int LEFT = 2;
    int UP = 3;
    void move(double xMove, double yMove);
    void resetPosition();
    void moveBack();
    double getSpeed();
}
