package Dungeon_Of_Forgotten_Treasures.States;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Enemy;
import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.StringDrawer;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.MenuUI.ClickListener;
import Dungeon_Of_Forgotten_Treasures.MenuUI.UIButton;
import Dungeon_Of_Forgotten_Treasures.MenuUI.UIManager;

import java.awt.*;

public class MenuState extends State {

    protected UIManager uiManager;
    public static int isInMenu; // 0 = enter menu; 1 = is in menu; 2 = exit menu; 3 = is not in menu; 4 = enter setari
    private UIButton start;
    private UIButton settings;
    private UIButton exit;
    //handler.getMouseManager().setUiManager(null);

    public MenuState(Handler handler) {
        super(handler);
        uiManager = UIManager.getSelf(handler);
        handler.getMouseManager().setUiManager(uiManager);
        isInMenu = 0;
        start = new UIButton((int)(handler.getGame().getWidth()/4 + handler.getGame().getWidth()/6),
                (int)(handler.getGame().getHeight()/3 +handler.getGame().getHeight()/6),
                316, 64, "Start",
                new ClickListener() {
                    @Override
                    public void onClick() {
                        isInMenu = 2;
                        }
                });

        settings = new UIButton((int)(handler.getGame().getWidth()/4+ handler.getGame().getWidth()/8),
                    (int)(handler.getGame().getHeight()/3 + +handler.getGame().getHeight()/4),
                    316, 64, "Options",
                    new ClickListener(){
                @Override
                public void onClick() {
                    isInMenu = 4; //enter setari
                    }
                });

        exit = new UIButton((int)(handler.getGame().getWidth()/4+ handler.getGame().getWidth()/6 + 10),
                (int)(handler.getGame().getHeight()/3 + handler.getGame().getHeight()/3),
                316, 64, "Exit",
                new ClickListener(){
            @Override
            public void onClick() {
                System.exit(0); //nice.
            }
        });

    }


    @Override
    public void Update()
    {

        if (isInMenu == 0)
        {
            isInMenu = 1;
            uiManager.addObject(start);
            uiManager.addObject(settings);
            uiManager.addObject(exit);

        }
        else if (isInMenu == 2)
        {
            isInMenu = 0;
            uiManager.removeObject(start);
            uiManager.removeObject(settings);
            uiManager.removeObject(exit);
            handler.getMouseManager().setUiManager(null);
            State.setCurrentState(handler.getGame().getGameState());

        }
        else if (isInMenu == 4)
        {
            uiManager.removeObject(start);
            uiManager.removeObject(settings);
            uiManager.removeObject(exit);
            SettingsState.isInSettings = 0;
            isInMenu = 3;
            State.setCurrentState(handler.getGame().getSettingsState());
        }


        if(handler.getMouseManager().getUiManager() == null) //daca a fost scos uiManager (ai intrat in gameState, si ai revenit)
            handler.getMouseManager().setUiManager(uiManager); //il resetezi
        uiManager.Update();
    }

    @Override
    public void Draw(Graphics graphics)
    {
        graphics.setColor(Color.black);
        //Curatare
        graphics.fillRect(0,0, handler.getGame().getWidth(), handler.getGame().getHeight());
        //Titlu
        StringDrawer.StringDraw(graphics, "Dungeon of ", handler.getGame().getWidth()/8, handler.getGame().getHeight()/5, Color.white, Assets.font72);
        StringDrawer.StringDraw(graphics, "      Forgotten Treasures", handler.getGame().getWidth()/8, handler.getGame().getHeight()/5 + 78, Color.white, Assets.font72);
        //Butoane
        uiManager.Draw(graphics);
    }

    @Override
    public void setEnemy(Enemy enemy) {

    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public int getIsInMenu() {
        return isInMenu;
    }

    public void setIsInMenu(int isInMenu) {
        this.isInMenu = isInMenu;
    }
}
