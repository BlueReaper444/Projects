package Dungeon_Of_Forgotten_Treasures.Entities.Factories;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Projectile;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.bigProjectile;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.mediumProjectile;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.smallProjectile;
import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Handler;

public class ProjectileFactory implements EntityFactory{

    @Override
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type)
    {
        return null;
    }

    @Override
    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type) {
        return null;
    }

    @Override
    public Projectile MakeProjectileEntity(Handler handler, float x, float y, boolean moveType, ProjectileEntityType type, short direction, int damage, boolean mainplayer) {
        switch(type)
        {
            case SMALLPROJECTILE: return new smallProjectile(handler, x, y, moveType,direction, damage, mainplayer);
            case MEDIUMPROJECTILE: return new mediumProjectile(handler, x, y, moveType,direction, damage, mainplayer);
            case BIGPROJECTILE: return new bigProjectile(handler, x, y, moveType,direction, damage, mainplayer);
            default: return null;
        }
    }
}
