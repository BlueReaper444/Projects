package Dungeon_Of_Forgotten_Treasures.UserInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, pressed_one_frame, cant_press;
    public  boolean up, down, left, right;
    public  boolean exit_to_menu;
    public  boolean take;

    //Singleton
    private static KeyManager self;

    private KeyManager()
    {
        keys = new boolean[256];//All keys baby!
        pressed_one_frame = new boolean[256];
        cant_press = new boolean[256]; //bool array by default is ALL FALSE.
    }

    public static KeyManager getSelf()
    {
        if (self == null)
            self = new KeyManager();
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void Update()
    {
        for (int i = 0; i<keys.length; i++)
        {
            if(cant_press[i] && !keys[i]) //daca a fost tinuta apasat, dar apoi i-a fost data drumul. //ar fi al treilea if care devine adevarat (pas3)
                cant_press[i] = false; //sa pot sa o apas iar. (se intampla in frameul imediat ce nu mai tii apasat tasta)
            else if (pressed_one_frame[i]) //daca a fost apasata pt un frame de Update();  Ar fi al doilea if care devine adevarat. (pas2)
            {
                cant_press[i] = true; //nu mai ai voie sa o apesi pana nu dai drumul cheii
                pressed_one_frame[i] = false; //devine false ptc VREI sa fie tinuta apasata DOAR 1 FRAME. ptc e pas2, asta e frame2, deci devine false :)
            }
            //primul if care devine adevarat (pas1)
            if (!cant_press[i] && keys[i]) //daca avem voie sa o apasam si e apasata.
                pressed_one_frame[i] = true; //ai apasat-o
        }
        //So what this does. Momentan cu keys doar aflii ce tasta e apasata ACUM.
        //Odata ce nu mai tii apasat pe ea, devine false in keys.
        //We want to open our inventory with one key press, and let it stay open until we press that key again to close it.
        //this does it.

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        exit_to_menu = keys[KeyEvent.VK_ESCAPE];
        take = keys[KeyEvent.VK_E];
    }
    //makes sure you press a key for ONLY ONE FRAME.
    public boolean keyPressedOneFrame(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return pressed_one_frame[keyCode]; //returneaza din just_pressed
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override //you pressed a key
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() < 0 || keyEvent.getKeyCode()>255)
            return;
        keys[keyEvent.getKeyCode()] = true;
        //fiecare tasta are un cod. getKeyCode ia codul din acel event
        //si se pune ca true in vectorul keys

    }

    @Override //stop pressing the key
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() < 0 || keyEvent.getKeyCode()>255)
            return;
        keys[keyEvent.getKeyCode()] = false;
    }
}
