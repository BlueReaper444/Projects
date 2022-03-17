package Dungeon_Of_Forgotten_Treasures.Entities.Factories;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Projectile;
import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Handler;

public interface EntityFactory {
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type);

    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type);

    public Projectile MakeProjectileEntity(Handler handler, float x, float y, boolean moveType, ProjectileEntityType type, short direction, int damage,boolean mainplayer);
}