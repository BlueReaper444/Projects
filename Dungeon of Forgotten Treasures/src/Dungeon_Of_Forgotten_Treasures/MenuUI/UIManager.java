package Dungeon_Of_Forgotten_Treasures.MenuUI;

import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
    private Handler handler;
    private ArrayList<UIObject> objects;

    //Singleton
    private static UIManager self;

    private UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }

    public static UIManager getSelf(Handler handler)
    {
        if (self == null)
            self = new UIManager(handler);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void Update(){
        if(!objects.isEmpty()) {
            for (UIObject object : objects)
                object.Update();
        }
    }

    public void Draw(Graphics graphics)
    {
        if(!objects.isEmpty()) {
            for (UIObject object : objects)
                object.Draw(graphics);
        }
    }

    public void onMouseMove(MouseEvent e)
    {
        if(!objects.isEmpty()) {
            for (UIObject object : objects)
                object.onMouseMove(e);
        }
    }
    public void onMouseRelease(MouseEvent e)
    {
        if(!objects.isEmpty()) {
            for (UIObject object : objects)
                object.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void removeObject(UIObject o){
        objects.remove(o);
    }


    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

}