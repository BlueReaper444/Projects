package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatDontMove;

import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Handler;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }
}
