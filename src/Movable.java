public interface Movable {
    /**
     * An integer that represent the orientation of right
     */
    int RIGHT = 0;
    /**
     * An integer that represent the orientation of down
     */
    int DOWN = 1;
    /**
     * An integer that represent the orientation of left
     */
    int LEFT = 2;
    /**
     * An integer that represent the orientation of up
     */
    int UP = 3;
    /**
     * A method that allows each movable objects to move
     */
    void move(double xMove, double yMove);

    /**
     * A method used to reset each movable objects to its starting position
     */
    void resetPosition();

    /**
     * A method that allows the movable objects to move back to its previous position
     */
    void moveBack();

    /**
     * A method used to get the speed of each movable objects
     */
    double getSpeed();
}
