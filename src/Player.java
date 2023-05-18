import bagel.*;
import bagel.util.Point;

public class Player implements Movable{
    private final static String PAC = "res/pac.png";
    private final static String PAC_OPEN = "res/pacOpen.png";
    private final static Image HEART = new Image("res/heart.png");
    private final static int SPEED = 3;
    private final static int FRENZY_SPEED = 4;
    private final static int MAX_LIVES = 3;
    private final static int SWITCH_FRAME = 15;
    private final static int FONT_SIZE = 20;
    private final static String SCORE_STRING = "SCORE ";
    private final static int SCORE_X = 25;
    private final static int SCORE_Y = 25;
    private final static int LIVES_X = 900;
    private final static int LIVES_Y = 10;
    private final static int LIVES_OFFSET = 30;
    private final Font FONT = new Font("res/FSO8BITR.ttf", FONT_SIZE);
    private final Point startingPosition;

    private DrawOptions rotator = new DrawOptions();
    private int counter;
    private int score;
    private Point position;
    private Point prevPosition;
    private Image currentImage;
    private int lives;
    private boolean isOpen = false;

    private int speed;

    /**
     * A constructor used to set up the starting conditions of the player
     */
    public Player(int initialX, int initialY){
        this.position = new Point(initialX, initialY);
        this.startingPosition = position;
        this.currentImage = new Image(PAC);
        this.counter = SWITCH_FRAME;
        this.lives = MAX_LIVES;
        this.score = 0;
        this.speed = SPEED;
    }

    /**
     * Method that performs state update
     */
    public void update(Input input, ShadowPac gameObject){
        counter--;
        if (input.isDown(Keys.UP)){
            move(0, -speed);
            rotator.setRotation(-Math.PI/2);
        } else if (input.isDown(Keys.DOWN)){
            move(0, speed);
            rotator.setRotation(Math.PI/2);
        } else if (input.isDown(Keys.LEFT)){
            move(-speed,0);
            rotator.setRotation(Math.PI);
        } else if (input.isDown(Keys.RIGHT)) {
            move(speed,0);
            rotator.setRotation(0);
        }
        if (counter == 0) {
            // switching the image being rendered
            if (isOpen) {
                currentImage = new Image(PAC);
                isOpen = false;
            } else {
                currentImage = new Image(PAC_OPEN);
                isOpen = true;
            }
            counter = SWITCH_FRAME;
        }
        currentImage.drawFromTopLeft(position.x, position.y, rotator);
        gameObject.checkCollisions(this);
        renderScore();
        renderLives();
    }

    /**
     * Method that moves the player given the direction
     */
    @Override
    public void move(double xMove, double yMove){
        prevPosition = position;
        position = new Point(position.x + xMove, position.y + yMove);
    }

    /**
     * Method that renders the player's score
     */
    private void renderScore(){
        FONT.drawString(SCORE_STRING + score, SCORE_X, SCORE_Y);
    }

    /**
     * Method that renders the player's lives
     */
    private void renderLives(){
        for (int i = 0; i < lives; i++){
            HEART.drawFromTopLeft(LIVES_X + (LIVES_OFFSET*i), LIVES_Y);
        }
    }

    /**
     * A method used to reset the player to its starting position and direction
     * when hitting by ghosts in standard mode
     */
    @Override
    public void resetPosition(){
        position = startingPosition;
        currentImage = new Image(PAC);
        rotator.setRotation(0);
    }

    /**
     * Method that prevents the player from colliding with the walls
     */
    @Override
    public void moveBack(){
        position = prevPosition;
    }

    /**
     * Method that checks if the player has 0 lives
     */
    public boolean isDead() {
        return lives == 0;
    }

    /**
     * Method that checks if the player has overcome the target score
     */
    public boolean overcomeScore(int target){
        return score >= target;
    }

    /**
     * Method that increase the score of the player
     */
    public void incrementScore(int points) {
        score += points;
    }

    public void reduceLives() {
        lives--;
    }

    public Point getPosition() {
        return position;
    }

    public Image getCurrentImage() {
        return currentImage;
    }
    /**
     * A method used to set the speed of the player into frenzy mode
     */
    public void setFrenzyMoveSize(){
        this.speed = FRENZY_SPEED;
    }
    /**
     * A method used to set the speed of the player back to original
     */
    public void setOriginalMoveSize(){
        this.speed = SPEED;
    }


    /**
     * A method used to get the speed of the player
     */
    @Override
    public double getSpeed(){
        return this.speed;
    }

}

