package Dungeon_Of_Forgotten_Treasures;

import Dungeon_Of_Forgotten_Treasures.Graphics.GameCamera;
import Dungeon_Of_Forgotten_Treasures.UserInput.KeyManager;
import Dungeon_Of_Forgotten_Treasures.UserInput.MouseManager;
import Dungeon_Of_Forgotten_Treasures.World.World;

//Contine o instanta de game si de world; It's for the sake of simplicity.
public class Handler {

    private Game game; // Game object
    private World world; // World object

    //Singleton
    private static Handler self; // Singleton

    private Handler(Game game) {
        this.game = game;
    }

    public static Handler getSelf(Game game)
    {
        if (self == null)
            self = new Handler(game);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public int getWidth() { return game.getWidth(); }

    public int getHeight()
    {
        return game.getHeight();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public MouseManager getMouseManager() {return game.getMouseManager(); }


    public void setGame(Game game) {
        this.game = game;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }


}