package Dungeon_Of_Forgotten_Treasures.Entities;

import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Player;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Projectile;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class ProjectileManager {

    private Handler handler;
    private Player player;
    private ArrayList<Projectile> Projectiles;

    private static ProjectileManager self;

    private ProjectileManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        Projectiles = new ArrayList<Projectile>();
    }

    public static ProjectileManager getSelf(Handler handler, Player player) {
        if (self == null)
            self = new ProjectileManager(handler, player);
        return self;
    }

    public static void reset() {
        self = null;
    }

    private Comparator<Projectile> DrawSort = new Comparator<Projectile>() {
        @Override
        public int compare(Projectile entity, Projectile t1) {
            if (entity.getY() + entity.getHeight() < t1.getY() + t1.getHeight())
                return -1; //daca prima entitate e mai sus (linia de jos a entitatii e mai mica / gen nu stiu.. degetele de la picioare), return nr negativ
            else if (entity.getY() + entity.getHeight() == t1.getY() + t1.getHeight())
                return 0;

            return 1; //daca a doua entitate e mai sus, return nr pozitiv
        }
    };

    public void Update() {
        ListIterator<Projectile> iter = Projectiles.listIterator();
        while (iter.hasNext()) {
            Projectile entity = iter.next();
            entity.Update();
            if (!entity.isActive())
                iter.remove();
        }
        //better with iterator, in case we remove an entity. less buggy.
        Projectiles.sort(DrawSort); //we're using our badboy here.
    }

    public void Draw(Graphics graphics) {
        for (Projectile entity : Projectiles) {
            entity.Draw(graphics);
        }
        player.drawInventoryAndStats(graphics);

    }

    public void addProjectile(Projectile Projectile) {
        Projectiles.add(Projectile);
    }

    public void removeProjectile(Projectile Projectile) {
        Projectiles.remove(Projectile);
    }

    public void clearManager() {
        Projectiles.clear();
    }

    //GETTERS AND SETTERS

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEntities(ArrayList<Projectile> Projectiles) {
        this.Projectiles = Projectiles;
    }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Projectile> getEntities() {
        return Projectiles;
    }
}