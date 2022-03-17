package Dungeon_Of_Forgotten_Treasures.States;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Enemy;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    protected Handler handler;

    public State(Handler handler)
    {
        this.handler = handler;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public static State getCurrentState()
    {
        return currentState;
    }

    public abstract void Update();

    public abstract void Draw(Graphics graphics);

    public abstract void setEnemy(Enemy enemy);
}
