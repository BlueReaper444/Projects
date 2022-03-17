package Dungeon_Of_Forgotten_Treasures.Tiles;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;

public class Wall_Gray_2_Tile extends Tile {
    public Wall_Gray_2_Tile(int id) {
        super(Assets.graywall_2, id);
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }
}