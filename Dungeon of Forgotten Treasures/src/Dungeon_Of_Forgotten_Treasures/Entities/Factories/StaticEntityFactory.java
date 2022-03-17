package Dungeon_Of_Forgotten_Treasures.Entities.Factories;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatDontMove.*;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Projectile;
import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Handler;

public class StaticEntityFactory implements EntityFactory {

    @Override
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type) {
        switch(type)
        {
            case HEALTHPOTION: return new HealthPotion(handler, x, y);
            case MANAPOTION: return new ManaPotion(handler, x, y);
            case MAXMANAPOTION: return new MaxManaPotion(handler, x, y);
            case MAXHEALTHPOTION: return new MaxHealthPotion(handler, x, y);
            case DOOR: return new Door(handler, x, y);
            case TORCHLIGHT: return new TorchLight(handler, x, y);
            default: return null;
        }
    }

    @Override
    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type) {
        return null;
    }

    @Override
    public Projectile MakeProjectileEntity(Handler handler, float x, float y, boolean moveType, ProjectileEntityType type, short direction, int damage,boolean mainplayer) {
        return null;
    }
}