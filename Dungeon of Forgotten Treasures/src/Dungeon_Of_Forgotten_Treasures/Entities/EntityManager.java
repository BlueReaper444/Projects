package Dungeon_Of_Forgotten_Treasures.Entities;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Player;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    //better than normal array (vector)
    //ne lasa sa adaugam si sa stergem cand/cate vrem
    private Comparator<Entity> DrawSort = new Comparator<Entity>(){
        @Override
        public int compare(Entity entity, Entity t1) {
            if(entity.getY() + entity.getHeight() < t1.getY() + t1.getHeight())
                return -1; //daca prima entitate e mai sus (linia de jos a entitatii e mai mica / gen nu stiu.. degetele de la picioare), return nr negativ
            else if(entity.getY() + entity.getHeight() == t1.getY() + t1.getHeight())
                return 0;

            return 1; //daca a doua entitate e mai sus, return nr pozitiv
        }
    };

    //^ basically, we're using Comparator to Sort our Array of entities.

    //Singleton
    private static EntityManager self;

    private EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public static EntityManager getSelf(Handler handler, Player player )
    {
        if(self == null)
            self = new EntityManager(handler, player);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void Update()
    {
        ListIterator<Entity> iter = entities.listIterator();
        while(iter.hasNext())
        {
            Entity entity = iter.next();
            entity.Update();
            if(!entity.isActive())
                iter.remove();
        }
        //better with iterator, in case we remove an entity. less buggy.
        entities.sort(DrawSort); //we're using our badboy here.
    }

    public void Draw(Graphics graphics)
    {
        for(Entity entity : entities)
        {
            entity.Draw(graphics);
        }
        player.drawInventoryAndStats(graphics);

    }

    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        entities.remove(entity);
    }

    public void clearManager()
    {
        entities.clear();
    }

    //GETTERS AND SETTERS

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }


}