package Dungeon_Of_Forgotten_Treasures.MenuUI;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.StringDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIButton extends UIObject {

    private String text;
    private ClickListener clicker;

    public UIButton(float x, float y, int width, int height,  String text, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.text = text;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics graphics) {
        if(mouse_hover)
            StringDrawer.StringDraw(graphics, ">" + text, (int) x, (int) y+50, Color.red, Assets.font64);
        else
            StringDrawer.StringDraw(graphics, text, (int) x, (int) y+50, Color.white, Assets.font64);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}