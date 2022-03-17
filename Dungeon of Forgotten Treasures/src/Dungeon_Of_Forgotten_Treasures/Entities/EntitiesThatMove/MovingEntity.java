package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;

public abstract class MovingEntity extends Entity {

    public static final float DEFAULT_SPEED = 2.0f; ///<Float makes it smooth
    public static final int DEFAULT_CREATURE_DEFENSE = 0;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;


    protected float speed;
    protected float xMove, yMove;
    protected int defense;

    public MovingEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        defense = DEFAULT_CREATURE_DEFENSE;
        hitable=true;
    }

    public void Move(){
        if(NoCollisionWithEntities(xMove, 0f)) //will it touch another's hitbox?
            moveX();
        if(NoCollisionWithEntities(0f, yMove)) //will it touch another's hitbox?
            moveY();
    }

    public void moveX(){
        if(xMove > 0) //moving right (x = x + xMove)
        {
            int tile_about_to_touch_x = (int) (x  + hitbox.x + hitbox.width + xMove) / Tile.TILEWIDTH; //coordonata x a tile-ului in care vei merge
            //x+hitbox.x = linia din stanga a dreptunghiului hitbox. adauga hitbox.width si e linia din dreapta. xMove e unde vrei sa ajungi, deci adaugi.
            //y+hitbox.y = linia de sus a dreptunghiului hitbox. adauga hitbox.height si ai linia de jos.
            if(!checkCollisionWithTile(tile_about_to_touch_x, (int)(y + hitbox.y)/Tile.TILEHEIGHT) && !checkCollisionWithTile(tile_about_to_touch_x, (int)(y + hitbox.y + hitbox.height)/Tile.TILEHEIGHT))
            {
                x+=xMove; //daca nu ai coliziune, mergi linistit.
            }
            else //in case we're about to go inside a block
            {
                x = tile_about_to_touch_x*Tile.TILEWIDTH - hitbox.x - hitbox.width - 1;//pune x in pozitia: linia din dreapta a hitboxului APROAPE atinge linia din stanga a tile-ului.
            }
        }
        else if (xMove <0) //moving left
        {
            int tile_about_to_touch_x = (int) (x  + hitbox.x + xMove) / Tile.TILEWIDTH; //coordonata x a tile-ului in care vei merge
            //x+hitbox.x = linia din stanga a dreptunghiului hitbox. adauga hitbox.width si e linia din dreapta. xMove e unde vrei sa ajungi, deci adaugi.
            //y+hitbox.y = linia de sus a dreptunghiului hitbox. adauga hitbox.height si ai linia de jos.
            if(!checkCollisionWithTile(tile_about_to_touch_x, (int)(y + hitbox.y)/Tile.TILEHEIGHT) && !checkCollisionWithTile(tile_about_to_touch_x, (int)(y + hitbox.y + hitbox.height)/Tile.TILEHEIGHT))
            {
                x+=xMove;
            }
            else//in case we're about to go inside a block
            {
                x = tile_about_to_touch_x*Tile.TILEWIDTH - hitbox.x + Tile.TILEWIDTH;//pune x in pozitia: linia din dreapta a hitboxului APROAPE atinge linia din dreapta a tile-ului.
            }
        }

    }

    public void moveY(){

        if(yMove > 0) //moving down
        {
            int tile_about_to_touch_y = (int) (y + hitbox.y + hitbox.height + yMove) / Tile.TILEHEIGHT; //coordonata y a tile-ului in care vei merge
            //y + hitbox.y + hitbox.height = linia de jos a dreptunghiului hitbox
            //x + hitbox.x = linia din stanga a drepunghiului hitbox. adauga hitbox.width si e linia din dreapta.
            if(!checkCollisionWithTile((int)(x + hitbox.x)/Tile.TILEHEIGHT, tile_about_to_touch_y)  && !checkCollisionWithTile((int)(x + hitbox.x + hitbox.width)/Tile.TILEHEIGHT, tile_about_to_touch_y ))
            {
                y+=yMove;
            }
            else//in case we're about to go inside a block
            {
                y = tile_about_to_touch_y* Tile.TILEHEIGHT - hitbox.y - hitbox.height - 1; //pune y in pozitia: linia de jos a hitboxului APROAPE atinge linia de sus a tile-ului.
            }

        }
        else if (yMove <0) //moving up
        {
            int tile_about_to_touch_y = (int) (y + hitbox.y + yMove) / Tile.TILEHEIGHT; //coordonata y a tile-ului in care vei merge
            //y + hitbox.y = linia de sus a dreptunghiului hitbox
            //x + hitbox.x = linia din stanga a drepunghiului hitbox. adauga hitbox.width si e linia din dreapta.
            if(!checkCollisionWithTile((int)(x + hitbox.x)/Tile.TILEWIDTH, tile_about_to_touch_y) && !checkCollisionWithTile((int)(x + hitbox.x + hitbox.width)/Tile.TILEHEIGHT, tile_about_to_touch_y ))
            {
                y+=yMove;
            }
            else//in case we're about to go inside a block
            {
                y = tile_about_to_touch_y*Tile.TILEHEIGHT - hitbox.y + Tile.TILEHEIGHT ; //pune y in pozitia: linia de sus a hitboxului APROAPE atinge linia de jos a tile-ului.
            }
        }
    }

    public void takeDamage(int damage)
    {
        if (damage>0) //if it's damage that has to be stopped by armor
        {
            if (defense < damage)
                health = health - damage + defense; //defense mitigates damage
            else
                health -= 1; //atleast 1 dmg

            if (health<=0)
            {
                health = 0;
                active = false;
                GetKilled();
            }
        }
        else
        {
            health -= damage;
            if (health>max_health)
            {
                health = max_health;
            }
        }
    }

    protected boolean checkCollisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x,y).isSolid();
    }

    //GETTERS, SETTERS

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getxMove() {
        return xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
