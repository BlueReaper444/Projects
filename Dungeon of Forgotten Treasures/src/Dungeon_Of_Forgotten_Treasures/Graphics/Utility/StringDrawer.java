package Dungeon_Of_Forgotten_Treasures.Graphics.Utility;

import java.awt.*;
import java.io.IOException;

public class StringDrawer {

    public static void StringDraw(Graphics graphics, String text, int x, int y, Color color, Font font)
    {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, x, y);

    }

    public static Font FontLoad(String path, float size)
    {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, StringDrawer.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        }
        catch (FontFormatException | IOException oof) //collapsed
        {
            oof.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}