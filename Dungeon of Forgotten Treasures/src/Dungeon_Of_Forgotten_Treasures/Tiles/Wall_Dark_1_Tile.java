package Dungeon_Of_Forgotten_Treasures.Tiles;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;

public class Wall_Dark_1_Tile extends Tile {
    public Wall_Dark_1_Tile(int id) {
        super(Assets.darkwall_1, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
