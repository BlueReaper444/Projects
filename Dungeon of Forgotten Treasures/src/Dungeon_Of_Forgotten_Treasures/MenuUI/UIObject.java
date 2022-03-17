package Dungeon_Of_Forgotten_Treasures.MenuUI;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {
    protected float x, y;
    protected int width, height;
    protected Rectangle hitbox;
    protected boolean mouse_hover = false;

    public UIObject(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void Update();

    public abstract void Draw(Graphics g);

    public abstract void onClick();

    //checks if mouse is on this UIObject
    public void onMouseMove(MouseEvent e)
    {
        if(hitbox.contains(e.getX(), e.getY()))
            mouse_hover = true;
        else
            mouse_hover = false;
        //daca mouse-ul e in hitbox-ul UIObject-ului
    }
    public void onMouseRelease(MouseEvent e)
    {
        if(mouse_hover)
            onClick();
        //daca suntem in buton si mouse-ul e released.
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMouse_hover(boolean mouse_hover) {
        this.mouse_hover = mouse_hover;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMouse_hover() {
        return mouse_hover;
    }
}