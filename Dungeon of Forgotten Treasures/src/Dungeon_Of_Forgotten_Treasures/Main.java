package Dungeon_Of_Forgotten_Treasures;

public class Main {
    public static void main(String[] args)
    {
        Game Dungeon = Game.getSelf("Dungeon of Forgotten Treasures", 1024,768);
        Dungeon.start();
    }
}
