import bagel.*;
import bagel.util.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2023
 * Please enter your name below
 * "I write the code for project 2 based on the code from the project 1 solution provided in LMS"
 * "I have also made an assumption that only one pellet will exist in level 1"
 * Yiyang Zhang
 */

public class ShadowPac extends AbstractGame  {

    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final static String LVL0_FILE = "res/level0.csv";
    private final static String LVL1_FILE = "res/level1.csv";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static int FRENZY_GHOST_SCORE = 30;
    private final static int TITLE_FONT_SIZE = 64;
    private final static int INSTRUCTION_FONT_SIZE = 24;
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 250;
    private final static int INS_X_OFFSET = 60;
    private final static int INS_Y_OFFSET = 190;
    private final static String INSTRUCTION_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE";
    private final static String INSTRUCTION_MESSAGE_LEVEL_1 =
            "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK";
    private final static String END_MESSAGE = "GAME OVER!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String COMPLETE_LEVEL_MESSAGE = "LEVEL COMPLETE";
    private final Font TITLE_FONT = new Font("res/FSO8BITR.ttf", TITLE_FONT_SIZE);
    private final Font INSTRUCTION_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE);
    private final static int LEVEL0_WIN_SCORE = 121;
    private final static int LEVEL1_WIN_SCORE = 800;
    private final static int MAX_FRENZY_FRAME = 1000;
    private final static int MAX_LEVEL_COMPLETE_TIME = 300;
    private final static double PINK_GHOST_SPEED = 3;
    private Player player;
    private ArrayList<Wall> walls;
    private ArrayList<Dot> dots;
    private ArrayList<Cherry> cherries;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Ghost> frenzyGhosts;
    private Pellet pellet;
    private boolean hasStarted;
    private boolean gameOver;
    private boolean playerWin;
    private static int currentLevel = 0;
    private boolean isFrenzy;
    private int frenzyFrame;
    private int levelCompleteFrame;

    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        setLevel();
        readLevel(LVL0_FILE);
    }

    private void setLevel(){
        hasStarted = false;
        gameOver = false;
        playerWin = false;
        isFrenzy = false;
        levelCompleteFrame = 0;

        dots = new ArrayList<>();
        cherries = new ArrayList<>();
        ghosts = new ArrayList<>();
        frenzyGhosts = new ArrayList<>();
        walls = new ArrayList<>();
    }


    /**
     * Method used to read file and create objects
     */
    private void readLevel(String File) {
        try (BufferedReader reader = new BufferedReader(new FileReader(File))){

            String line;

            while((line = reader.readLine()) != null){
                String[] sections = line.split(",");
                switch (sections[0]) {
                    case "Player":
                        player = new Player(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "Ghost":
                        ghosts.add(new RedGhost(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]), 0));
                        break;
                    case "GhostRed":
                        ghosts.add(new RedGhost(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]),1));
                        break;
                    case "GhostBlue":
                        ghosts.add(new BlueGhost(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "GhostGreen":
                        ghosts.add(new GreenGhost(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "GhostPink":
                        ghosts.add(new PinkGhost(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "Dot":
                        dots.add(new Dot(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "Cherry":
                        cherries.add(new Cherry(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                    case "Pellet":
                        pellet = new Pellet(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "Wall":
                        walls.add(new Wall(Integer.parseInt(sections[1]), Integer.parseInt(sections[2])));
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
            if (currentLevel == 1) {
                drawMessage(WIN_MESSAGE);
            }
            else{
                drawMessage(COMPLETE_LEVEL_MESSAGE);
                if (levelCompleteFrame == MAX_LEVEL_COMPLETE_TIME){
                    currentLevel = 1;
                    setLevel();
                    readLevel(LVL1_FILE);
                }
                else{
                    levelCompleteFrame++;
                }
            }

        } else {
            player.update(input, this);

            if (isFrenzy){
                player.setFrenzyMoveSize();
                frenzyFrame++;
                if (frenzyFrame == MAX_FRENZY_FRAME){
                    stopFrenzy();
                    player.setOriginalMoveSize();
                }
                for (Ghost current: frenzyGhosts) {
                    current.update();
                }
            }
            else{
                for (Ghost current: ghosts) {
                    current.update();
                }
            }
            for (Wall current: walls){
                current.update();
            }
            for(Dot current: dots){
                current.update();
            }
            if (currentLevel == 1) {
                for (Cherry current : cherries) {
                    current.update();
                }
                pellet.update();
            }
            if (player.isDead()){
                gameOver = true;
            }
            checkPlayerWin();
        }
    }

    /**
     * Method that checks for collisions between the player and the other entities, and performs
     * corresponding actions.
     */
    public void checkCollisions(Player player){
        Rectangle playerBox = new Rectangle(player.getPosition(), player.getCurrentImage().getWidth(),
                player.getCurrentImage().getHeight());

        if(isFrenzy){
            checkGhostsCollision(frenzyGhosts, playerBox);
        }
        else{
            checkGhostsCollision(ghosts, playerBox);
        }

        for (Dot current: dots){
            Rectangle dotBox = current.getBoundingBox();
            if (current.isActive() && playerBox.intersects(dotBox)) {
                player.incrementScore(current.getScore());
                current.setActive(false);
            }
        }

        for (Wall current: walls){
            Rectangle wallBox = current.getBoundingBox();
            if (playerBox.intersects(wallBox)){
                player.moveBack();
            }
        }
        if (currentLevel == 1) {
            for (Cherry current: cherries){
                Rectangle CherryBox = current.getBoundingBox();
                if (current.isActive() && playerBox.intersects(CherryBox)) {
                    player.incrementScore(current.getScore());
                    current.setActive(false);
                }
            }

            Rectangle pelletBox = pellet.getBoundingBox();
            if (pellet.isActive() && playerBox.intersects(pelletBox)) {
                pellet.setActive(false);
                isFrenzy = true;
                frenzyActivation();
            }
        }
    }

    private void checkGhostsCollision(ArrayList<Ghost> ghosts, Rectangle playerBox){
        for (Ghost current: ghosts){
            Rectangle ghostBox = current.getBoundingBox();
            if (playerBox.intersects(ghostBox)){
                if(isFrenzy) {
                    if(current.isActive()) {
                        player.incrementScore(FRENZY_GHOST_SCORE);
                        current.setActive(false);
                    }
                }
                else {
                    player.reduceLives();
                    player.resetPosition();
                    current.resetPosition();
                }

            }
            for (Wall theWall: walls){
                Rectangle theWallBox = theWall.getBoundingBox();
                if (ghostBox.intersects(theWallBox)){
                    if (currentLevel == 1) {
                        current.changeOrient();
                        current.moveBack();
                    }
                }
            }
        }
    }

    private void checkPlayerWin(){
        if (currentLevel == 0){
            if (player.overcomeScore(LEVEL0_WIN_SCORE)){
                playerWin = true;
            }
        }
        else{
            if (player.overcomeScore(LEVEL1_WIN_SCORE)){
                playerWin = true;
            }
        }
    }
    private void frenzyActivation(){
        frenzyFrame = 0;
        for (Ghost current: ghosts){
            if (current.getSpeed() == PINK_GHOST_SPEED) {
                frenzyGhosts.add(new FrenzyGhost(current, true));
            }
            else{
                frenzyGhosts.add(new FrenzyGhost(current, false));
            }
        }
    }

    private void stopFrenzy(){
        this.isFrenzy = false;
        for (Ghost current: ghosts){
            current.resetPosition();
        }
    }

    /**
     * Method used to draw the start screen title and instructions
     */
    private void drawStartScreen(){
        if (currentLevel == 0) {
            TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
            INSTRUCTION_FONT.drawString(INSTRUCTION_MESSAGE, TITLE_X + INS_X_OFFSET, TITLE_Y + INS_Y_OFFSET);
        }
        else{
            INSTRUCTION_FONT.drawString(INSTRUCTION_MESSAGE_LEVEL_1, TITLE_X + INS_X_OFFSET, TITLE_Y + INS_Y_OFFSET);
        }
    }

    /**
     * Method used to draw end screen messages
     */
    private void drawMessage(String message){
        TITLE_FONT.drawString(message, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(message)/2.0)),
                (Window.getHeight()/2.0 + (TITLE_FONT_SIZE/2.0)));
    }
}