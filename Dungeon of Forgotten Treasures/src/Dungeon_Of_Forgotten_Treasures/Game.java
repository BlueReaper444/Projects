package Dungeon_Of_Forgotten_Treasures;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Graphics.GameCamera;
import Dungeon_Of_Forgotten_Treasures.States.*;
import Dungeon_Of_Forgotten_Treasures.UserInput.KeyManager;
import Dungeon_Of_Forgotten_Treasures.UserInput.MouseManager;
import Dungeon_Of_Forgotten_Treasures.Window.GameWindow;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private GameWindow window;
    private int width;
    private int height;
    private String title;

    private static Game self;

    private Thread thread;
    private boolean isRunning = false;

    private BufferStrategy bs; //a way for the PC to draw stuff on screen
    private Graphics graphics; //the paint brush used to paint on that canvas of GameWindow

    private KeyManager keyManager; //for almost everything that makes this a game
    private MouseManager mouseManager; //for main menu

    private State gameState; //the state in which you move, do stuff
    private State menuState; //main menu; has mouse buttons
    private State settingsState; //a description of controls
    private State endgameState; //the state after the game end
    private State wingameState; // the state where the player win

    private GameCamera gameCamera; //the camera

    private Handler handler; //helper

    private Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public static Game getSelf(String title, int width, int height)
    {
        if (self == null)
            self = new Game(title, width, height);
        return self;
    }

    public synchronized void start(){
        if (isRunning) //game is already running. get out.
            return;

        isRunning = true;
        thread = new Thread(this); //constructor: so, what class you wanna run separately?
        thread.start(); //this little method will call the run() from Game
    }

    public synchronized void stop(){
        if (!isRunning) //if it's already closed
            return;

        isRunning = false;
        try
        {
            thread.join(); //closes thread
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

    private void init()
    {
        window = GameWindow.getSelf(title, width, height);//the window
        keyManager = KeyManager.getSelf();
        mouseManager = MouseManager.getSelf();
        window.getFrame().addKeyListener(keyManager);
        window.getFrame().addMouseListener(mouseManager);
        window.getFrame().addMouseMotionListener(mouseManager);
        window.getCanvas().addMouseListener(mouseManager);
        window.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init(); //initializes assets

        handler = Handler.getSelf(this);
        gameCamera = GameCamera.getSelf(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        endgameState = new EndGameState(handler);
        wingameState = new WinGameState(handler);

        State.setCurrentState(menuState);
    }

    public void update()
    {
        keyManager.Update();

        if(State.getCurrentState()!= null)
        {
            State.getCurrentState().Update();
        }
    }

    public void draw()
    {
        bs = window.getCanvas().getBufferStrategy();
        if (bs == null)
        {
            window.getCanvas().createBufferStrategy(4); //creates bs with 4 buffers.
            return; //get outta here
        }

        graphics = bs.getDrawGraphics(); //the paint brush for your canvas.

        //before drawing, you gotta clear canvas.
        graphics.clearRect(0, 0, width, height); //from (0.0) it will create a clear rectangle with l and L = width and height; width and height just get added to x and y;
        //In Java: (0,0) is left right. y increases downwards. x is normal (left -> right)

        //NOW DRAW, PARTNER!
        if(State.getCurrentState()!= null)
        {
            State.getCurrentState().Draw(graphics);
        }

        //ENOUGH DRAWING!
        bs.show(); //does the magic with buffers.
        graphics.dispose(); //BEGONE!!!
    }

    @Override
    public void run() //the main method. our code will be here. will use init, draw and update
    {
        init(); //makes window, and other stuff

        final int fps = 60;
        final double timePerUpdate = 1000000000 / fps; //1 frame in nanoseconds

        double delta = 0;
        long nowTime;
        long oldTime = System.nanoTime();

        while (isRunning) //while the game is running
        {
            nowTime = System.nanoTime();
            delta += (nowTime - oldTime) / timePerUpdate; //tells the pc when/when not to call update/draw
            oldTime = nowTime;


            if (delta >= 1) { //a ajuns la o secunda
                update();
                draw();
                delta--; //s-a rendat, deci readucem la 0
            }
        }

        stop(); //just in case we didn't stop once running = false
    }


    public State getGameState() {
        return gameState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getSettingsState() {
        return settingsState;
    }

    public State getEndgameState() { return endgameState; }

    public State getWingameState() { return wingameState; }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

}
