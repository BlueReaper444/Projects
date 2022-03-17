package Dungeon_Of_Forgotten_Treasures.World.EntityPlacement;

import Dungeon_Of_Forgotten_Treasures.Entities.EntityManager;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.EntityFactory;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.MovingEntityType;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.StaticEntityType;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Light;

public class WorldEntities {

    public static void map1Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving) {
        final int tSize = 64;

        //DOOR
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 12, tSize, StaticEntityType.DOOR));

        //Items
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 8, tSize * 8, StaticEntityType.HEALTHPOTION));
        //entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 1, tSize * 8, StaticEntityType.TORCHLIGHT));

        //entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 1, tSize * 8, StaticEntityType.TORCHLIGHT));

        //Enemies
    }

    public static void map2Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving) {
        final int tSize = 64;

        //DOOR
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 16, tSize, StaticEntityType.DOOR));

        //Items
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 20, tSize * 2, StaticEntityType.HEALTHPOTION));
        //Enemies
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 21, tSize * 15, true, MovingEntityType.MINION));

    }

    public static void map3Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving) {
        final int tSize = 64;
        //DOOR
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 44, tSize * 14, StaticEntityType.DOOR));

        //Items
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 36, tSize * 6, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 30, tSize * 18, StaticEntityType.MANAPOTION));
        //Enemies
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 22, tSize * 3, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 30, tSize * 4, true, MovingEntityType.SHADOW));
    }

    public static void map4Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving) {
        final int tSize = 64;
        //DOOR
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 20, tSize * 2, StaticEntityType.DOOR));

        //Items
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 5, tSize * 6, StaticEntityType.MAXHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 36, tSize * 5, StaticEntityType.MANAPOTION));
        //Enemies
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 8, tSize * 4, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 10, tSize * 5, true, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 30, tSize * 5, true, MovingEntityType.MINION));
    }

    public static void map5Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving) {
        final int tSize = 64;
        //DOOR
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 10, tSize * 2, StaticEntityType.DOOR));

        //Items
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 8, tSize * 5, StaticEntityType.MAXHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 13, tSize * 5, StaticEntityType.MAXMANAPOTION));
        //Enemies
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 8, tSize * 13, false, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 13, tSize * 15, false, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 16, tSize * 14, false, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 18, tSize * 11, false, MovingEntityType.MINION));

    }

    public static void map6Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving) {
        final int tSize = 64;
        //DOOR
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 27, tSize * 7, StaticEntityType.DOOR));

        //Items
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 5, tSize * 8, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize * 5, tSize * 13, StaticEntityType.MANAPOTION));
        //Enemies
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 20, tSize * 17, false, MovingEntityType.BOSS));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 17, tSize * 7, false, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize * 17, tSize * 14, false, MovingEntityType.SHADOW));
    }
}