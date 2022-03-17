package Dungeon_Of_Forgotten_Treasures.Graphics.Utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage (String path ) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path)); //this is how we load an image in Java.
        }
        catch (IOException oof)
        {
            //afiseaza de ce a crapat
            oof.printStackTrace();
            System.exit(1); //exits game with code 1. if it failed to load, don't open game.
        }
        return null;
    }
}