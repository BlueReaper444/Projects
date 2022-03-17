package Dungeon_Of_Forgotten_Treasures.Graphics.Factory;

import Dungeon_Of_Forgotten_Treasures.Graphics.SpriteSheet;

import java.awt.image.BufferedImage;

public class SpriteSheetFactory {
    public SpriteSheet makeSheet(BufferedImage image)
    {
        return new SpriteSheet(image);
    }
}