package Dungeon_Of_Forgotten_Treasures.Entities;

import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;

public abstract class Entity {
    public static final int DEFAULT_HEALTH = 100;

    protected float x; //float = smooth movement
    protected float y; //x, y = position
    protected int width;
    protected int height;
    protected int max_health;
    protected int health;
    protected int defence;
    protected Handler handler;
    protected Rectangle hitbox;
    protected boolean active = true; //if it's not active, gets removed from game by EntityManager
    protected boolean hitable = false; //if it's not active, can't ne hit
    protected boolean mainplayer = false;

    public Entity(Handler handler, float x, float y, int width, int height){
        max_health = DEFAULT_HEALTH;
        health = DEFAULT_HEALTH;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        hitbox = new Rectangle(0,0, width, height); //hitbox al entitatii de marimea ei
    }

    //Makes sure there's no collision of an entity with any other entity
    public boolean NoCollisionWithEntities(float xOffset, float yOffset)
    {
        for (Entity entity: handler.getWorld().getEntityManager().getEntities())
        {
            if(!entity.equals(this)) {
                if (entity.getHitBox(0f, 0f).intersects(getHitBox(xOffset, yOffset))) //daca hitbox-urile entitatilor se ating de hitbox-ul + offset al entitatii curente
                    return false; //va fi coliziune deci false
            }
        }
        return true;
    }

    //gets "future" hitbox
    public Rectangle getHitBox(float xOffset, float yOffset) //get "future" hitbox
    {
        return new Rectangle((int)(x + hitbox.x + xOffset), (int)(y+hitbox.y+yOffset), hitbox.width,hitbox.height );
        //basically: x+hitbox.x = exact unde e hitbox-ul. la fel si cu y. xOffset e o "predictie" inainte sa se intample.
    }

    //all entities die, and do something. Potions die when taken. Player and Enemies die in combat.
    public abstract void GetKilled();
    public abstract void Update();
    public abstract void Draw(Graphics graphics);


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHealth() {
        return health;
    }

    public boolean isActive() {
        return active;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public boolean getHitable() { return hitable; }

    public void setHitable(boolean hitable) {
        this.hitable = hitable;
    }

    public int getDefence() { return defence; }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setMainplayer(boolean mainplayer) {
        this.mainplayer = mainplayer;
    }

    public boolean getMainplayer() { return mainplayer; }
}