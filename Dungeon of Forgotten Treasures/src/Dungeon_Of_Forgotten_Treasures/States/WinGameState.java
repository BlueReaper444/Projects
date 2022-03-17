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

public class WinGameState extends State{
    protected UIManager uiManager;
    public static int isInEndState;
    //0 = entering end menu; 1 = in end menu; 2 =  back to start; 3 = out of settings;
    private UIButton exitGame;

    public WinGameState(Handler handler) {
        super(handler);
        uiManager = UIManager.getSelf(handler);
        handler.getMouseManager().setUiManager(uiManager);
        isInEndState = 0;
        exitGame = new UIButton((int)(handler.getGame().getWidth()/4+ handler.getGame().getWidth()/6 + 10),
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
        if (isInEndState == 0) //if entering
        {
            isInEndState = 1; //is in
            uiManager.addObject(exitGame);
        }
        else if (isInEndState == 2) //back to menu
        {
            isInEndState = 3;
            MenuState.isInMenu = 0;
            uiManager.removeObject(exitGame);
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

        StringDrawer.StringDraw(graphics, "Congratulations!", handler.getGame().getWidth()/3, handler.getGame().getHeight()/4, Color.white, Assets.font72);
        StringDrawer.StringDraw(graphics, "You Win!", handler.getGame().getWidth()/3, handler.getGame().getHeight()/3 + 78, Color.white, Assets.font72);

        StringDrawer.StringDraw(graphics, "Final score: "+handler.getWorld().getEntityManager().getPlayer().getScore(), handler.getGame().getWidth()/3, handler.getGame().getHeight()/4 + 225, Color.white, Assets.font72);

        uiManager.Draw(graphics);
    }
    @Override
    public void setEnemy(Enemy enemy) {

    }
}
