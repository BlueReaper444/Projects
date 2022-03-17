package Dungeon_Of_Forgotten_Treasures.Graphics;

import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;

public class GameCamera {

    private float xOffset, yOffset;
    private Handler handler;

    //Singleton
    private static GameCamera self;

    private GameCamera(Handler handler, float xOffset, float yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.handler = handler;
    }

    public static GameCamera getSelf(Handler handler, float xOffset, float yOffset)
    {
        if (self == null)
            self = new GameCamera(handler, xOffset, yOffset);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void checkBlankSpace()
    {
        if (xOffset<0) //limita din stanga a hartii
            xOffset = 0;
        else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth())  //limita din dreapta a hartii
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth(); //latimea lumii in pixeli - latimea window-ului.

        if (yOffset<0) //limita de sus a hartii
            yOffset = 0;
        else if(yOffset > handler.getWorld().getHeight() * Tile.TILEWIDTH - handler.getHeight()) //limita de jos a hartii
            yOffset = handler.getWorld().getHeight() * Tile.TILEWIDTH - handler.getHeight(); // latimea lumii in pixeli - inaltimea window-ului.
    }


    public void move(float x, float y)
    {
        xOffset += x;
        yOffset += y;
        checkBlankSpace(); //daca esti la limita, ramane acolo harta.
    }

    public void FocusEntity(Entity entity)
    {
        xOffset = entity.getX() - handler.getWidth() / 2 + entity.getWidth()/2; //- jumate din ecran, ca entitatea sa fie in mijloc; + jumate din dimensiunea entitatii
        yOffset = entity.getY() - handler.getHeight() / 2 + entity.getHeight()/2;
        checkBlankSpace();
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }
}
