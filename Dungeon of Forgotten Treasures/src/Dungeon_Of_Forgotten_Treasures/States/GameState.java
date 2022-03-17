package Dungeon_Of_Forgotten_Treasures.States;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Enemy;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.World.World;

import java.awt.*;

public class GameState extends State {


    private World map1;

    public GameState(Handler handler)
    {
        super(handler);
        map1 = World.getSelf( handler,"res/worlds/map1.txt");
        handler.setWorld(map1);

    }

    @Override
    public void Update()
    {
        map1.Update();
    }

    @Override
    public void Draw(Graphics graphics)
    {
        map1.Draw(graphics);
    }

    @Override
    public void setEnemy(Enemy enemy) {

    }
}