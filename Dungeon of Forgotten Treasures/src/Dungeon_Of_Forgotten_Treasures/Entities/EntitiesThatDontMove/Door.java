package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatDontMove;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.States.State;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;

import java.awt.*;

public class Door extends StaticEntity{

    private boolean isOpen;
    public Door(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        hitbox.x =16;
        hitbox.y = 26;
        hitbox.width = 32;
        hitbox.height = 12;
        max_health = 99999;
        health = 99999;
        isOpen = false;
    }
    @Override
    public void GetKilled() {
        if (isOpen) {
            if (handler.getWorld().getMapNumber() == 7) {
                State.setCurrentState(handler.getGame().getWingameState());
            } else {
                handler.getWorld().loadAWorld("res/worlds/map" + handler.getWorld().getMapNumber() + ".txt");
            }
        }
    }

    @Override
    public void Update() {
        if (handler.getWorld().getEntityManager().getEntities().size() == 2) //player + usa
        {
            isOpen = true;
        }
        if (isOpen)
        {
            health = 1;
        }
    }

    @Override
    public void Draw(Graphics graphics) {
        if (!isOpen)
        {
            graphics.drawImage(Assets.door_1, (int)(x- handler.getGameCamera().getxOffset()),  (int)(y- handler.getGameCamera().getyOffset()), width, height, null);
        }
        else
        {
            graphics.drawImage(Assets.door_2, (int)(x- handler.getGameCamera().getxOffset()),  (int)(y- handler.getGameCamera().getyOffset()), width, height, null);
        }
    }
}
