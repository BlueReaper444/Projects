package Dungeon_Of_Forgotten_Treasures.States;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Enemy;
import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.StringDrawer;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.MenuUI.ClickListener;
import Dungeon_Of_Forgotten_Treasures.MenuUI.UIButton;
import Dungeon_Of_Forgotten_Treasures.MenuUI.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SettingsState extends State{

    protected UIManager uiManager;
    public static int isInSettings;
    private UIButton exitToMenu; //0 = entering settings; 1 = in settings; 2 = exiting settings; 3 = out of settings;

    public SettingsState(Handler handler) {
        super(handler);
        uiManager = UIManager.getSelf(handler);
        handler.getMouseManager().setUiManager(uiManager);
        isInSettings = 3;
        exitToMenu = new UIButton((int)(handler.getGame().getWidth()/4), (int)(handler.getGame().getHeight()/2 +handler.getGame().getHeight()/6), 316, 64, "Back to menu", new ClickListener(){
            @Override
            public void onClick() {
                isInSettings = 2;
            }
        });
    }

    @Override
    public void Update()
    {
        if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_ESCAPE))
            isInSettings = 2;


        if (isInSettings == 0) //if entering
        {
            isInSettings = 1; //is in
            //uiManager = UIManager.getSelf(handler);
            //handler.getMouseManager().setUiManager(uiManager);
            uiManager.addObject(exitToMenu);
        }
        else if (isInSettings == 2) //if leaving
        {
            isInSettings = 3;
            MenuState.isInMenu = 0;
            uiManager.removeObject(exitToMenu);
            State.setCurrentState(handler.getGame().getMenuState());
        }

        if(handler.getMouseManager().getUiManager() == null) //daca a fost scos uiManager (ai intrat in gameState, si ai revenit)
            handler.getMouseManager().setUiManager(uiManager); //il resetezi
        uiManager.Update();
    }

    @Override
    public void Draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());

        graphics.drawImage(Assets.background, 200, 125, 640, 480, null);
        StringDrawer.StringDraw(graphics, "Controls", 200 + 40, 125 + 40, Color.white, Assets.font36);
        StringDrawer.StringDraw(graphics, "W - move up", 200 + 40, 125 + 40 * 2, Color.white, Assets.font36);
        StringDrawer.StringDraw(graphics, "A - move to the left", 200 + 40, 125 + 40 * 3, Color.white, Assets.font36);
        StringDrawer.StringDraw(graphics, "S - move down", 200 + 40, 125 + 40 * 4, Color.white, Assets.font36);
        StringDrawer.StringDraw(graphics, "D - move to the right", 200 + 40, 125 + 40 * 5, Color.white, Assets.font36);
        StringDrawer.StringDraw(graphics, "Q - open / close inventory", 200 + 40, 125 + 40 * 6, Color.white, Assets.font36);
        StringDrawer.StringDraw(graphics, "E - interact / attack", 200 + 40, 125 + 40 * 7, Color.white, Assets.font36);

        uiManager.Draw(graphics);
    }
    @Override
    public void setEnemy(Enemy enemy) {

    }
}