package Dungeon_Of_Forgotten_Treasures.Entities.Factories;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.BOSS;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Projectile;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Shadow;
import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Minion;
import Dungeon_Of_Forgotten_Treasures.Handler;

public class MovingEntityFactory implements EntityFactory {

    @Override
    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type) {

        switch(type)
        {
            case MINION: return new Minion(handler, x, y, moveType);
            case SHADOW: return new Shadow(handler, x, y, moveType);
            case BOSS: return new BOSS(handler, x, y, moveType);
            default: return null;
        }
    }

    @Override
    public Projectile MakeProjectileEntity(Handler handler, float x, float y, boolean moveType, ProjectileEntityType type, short direction, int damage,boolean mainplayer) {
        return null;
    }

    @Override
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type)
    {
        return null;
    }

}
