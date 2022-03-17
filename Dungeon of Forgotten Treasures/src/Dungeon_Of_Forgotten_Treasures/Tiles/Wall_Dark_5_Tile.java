package Dungeon_Of_Forgotten_Treasures.Tiles;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;

public class Wall_Dark_5_Tile extends Tile {
    public Wall_Dark_5_Tile(int id) {
        super(Assets.darkwall_5, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
