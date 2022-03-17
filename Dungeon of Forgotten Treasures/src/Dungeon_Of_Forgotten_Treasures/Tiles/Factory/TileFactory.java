package Dungeon_Of_Forgotten_Treasures.Tiles.Factory;

import Dungeon_Of_Forgotten_Treasures.Tiles.*;

public class TileFactory {
    public Tile makeTile(TileType type, int id) throws NullPointerException
    {
        try {
            switch (type) {
                case GROUND_1:
                    return new Ground_1_Tile(id);
                case GROUND_2:
                    return new Ground_2_Tile(id);
                case GROUND_3:
                    return new Ground_3_Tile(id);
                case WATER_TILE:
                    return new Water_Tile(id);
                case GROUND_STAIRS:
                    return new Ground_Stairs(id);
                case WALL_1:
                    return new Wall_Gray_1_Tile(id);
                case WALL_2:
                    return new Wall_Gray_2_Tile(id);
                case WALL_3:
                    return new Wall_Gray_3_Tile(id);
                case WALL_4:
                    return new Wall_Gray_4_Tile(id);
                case WALL_5:
                    return new Wall_Gray_5_Tile(id);
                case MOSSY_1:
                    return new Wall_Dark_1_Tile(id);
                case MOSSY_2:
                    return new Wall_Dark_2_Tile(id);
                case DOOR_1:
                    return new Door_1_Tile(id);
                case DOOR_2:
                    return new Door_2_Tile(id);
                /*

                case GROUND_1:
                    return new Ground_1_Tile(id);
                case GROUND_2:
                    return new Ground_2_Tile(id);
                case GROUND_3:
                    return new Ground_3_Tile(id);
                case LAVA_1:
                    return new Lava_1_Tile(id);
                case LAVA_2:
                    return new Lava_2_Tile(id);
                case LAVA_3:
                    return new Lava_3_Tile(id);
                case MOSSY_1:
                    return new MossyWall_1_Tile(id);
                case MOSSY_2:
                    return new MossyWall_2_Tile(id);
                case MOSSY_3:
                    return new MossyWall_3_Tile(id);
                case WALL_1:
                    return new Wall_1_Tile(id);
                case WALL_2:
                    return new Wall_2_Tile(id);
                case WALL_3:
                    return new Wall_3_Tile(id);
                    */
                default:
                    return null;

            }
        }
        catch(NullPointerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}