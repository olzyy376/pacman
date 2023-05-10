import bagel.*;
import bagel.util.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2023
 * Please enter your name below
 * Yiyang Zhang
 */

public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final static String WORLD_FILE = "res/level0.csv";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static int TITLE_FONT_SIZE = 64;
    private final static int INSTRUCTION_FONT_SIZE = 24;
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 250;
    private final static int INS_X_OFFSET = 60;
    private final static int INS_Y_OFFSET = 190;
    private final static String INSTRUCTION_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE";
    private final static String END_MESSAGE = "GAME OVER!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final Font TITLE_FONT = new Font("res/FSO8BITR.ttf", TITLE_FONT_SIZE);
    private final Font INSTRUCTION_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE);

    private final static int WALL_ARRAY_SIZE = 145;
    private final static int DOT_ARRAY_SIZE = 121;
    private final static int GHOST_ARRAY_SIZE = 4;
    private final static Wall[] walls = new Wall[WALL_ARRAY_SIZE];
    private final static Dot[] dots = new Dot[DOT_ARRAY_SIZE];
    private final static Ghost[] ghosts = new Ghost[GHOST_ARRAY_SIZE];
    private Player player;
    private boolean hasStarted;
    private boolean gameOver;
    private boolean playerWin;

    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        readCSV();
        hasStarted = false;
        gameOver = false;
        playerWin = false;
    }

    /**
     * Method used to read file and create objects
     */
    private void readCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(WORLD_FILE))){

            String line;
            int currentWallCount = 0;
            int currentDotCount = 0;
            int currentGhostCount = 0;

            while((line = reader.readLine()) != null){
                String[] sections = line.split(",");
                switch (sections[0]) {
                    case "Player":
                        player = new Player(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "Ghost":
                        ghosts[currentGhostCount] = new Ghost(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]));
                        currentGhostCount++;
                        break;
                    case "Dot":
                        dots[currentDotCount] = new Dot(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]));
                        currentDotCount++;
                        break;
                    case "Wall":
                        walls[currentWallCount] = new Wall(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]));
                        currentWallCount++;
                        break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * Method that performs state update
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if(!hasStarted){
            drawStartScreen();
            if (input.wasPressed(Keys.SPACE)){
                hasStarted = true;
            }

        } else if (gameOver){
            drawMessage(END_MESSAGE);

        } else if (playerWin) {
            drawMessage(WIN_MESSAGE);

        } else {
            player.update(input, this);

            for (Ghost current: ghosts){
                current.update();
            }

            for(Wall current: walls){
                current.update();
            }
            for(Dot current: dots){
                current.update();
            }

            if (player.isDead()){
                gameOver = true;
            }
            if (player.reachedScore(DOT_ARRAY_SIZE)){
                playerWin = true;
            }
        }
    }

    /**
     * Method that checks for collisions between the player and the other entities, and performs
     * corresponding actions.
     */
    public void checkCollisions(Player player){
        Rectangle playerBox = new Rectangle(player.getPosition(), player.getCurrentImage().getWidth(),
                player.getCurrentImage().getHeight());

        for (Ghost current: ghosts){
            Rectangle ghostBox = current.getBoundingBox();
            if (playerBox.intersects(ghostBox)){
                player.reduceLives();
                player.resetPosition();
            }
        }

        for (Dot current: dots){
            Rectangle dotBox = current.getBoundingBox();
            if (current.isActive() && playerBox.intersects(dotBox)) {
                player.incrementScore();
                current.setActive(false);
            }
        }

        for (Wall current: walls){
            Rectangle wallBox = current.getBoundingBox();
            if (playerBox.intersects(wallBox)){
                player.moveBack();
            }
        }
    }

    /**
     * Method used to draw the start screen title and instructions
     */
    private void drawStartScreen(){
        TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_MESSAGE,TITLE_X + INS_X_OFFSET, TITLE_Y + INS_Y_OFFSET);
    }

    /**
     * Method used to draw end screen messages
     */
    private void drawMessage(String message){
        TITLE_FONT.drawString(message, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(message)/2.0)),
                (Window.getHeight()/2.0 + (TITLE_FONT_SIZE/2.0)));
    }
}