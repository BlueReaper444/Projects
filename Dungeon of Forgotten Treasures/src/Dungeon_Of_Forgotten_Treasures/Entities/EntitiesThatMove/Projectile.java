package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Graphics.Animations;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;

public abstract class Projectile extends MovingEntity{
    protected int damage;
    protected short direction;
    protected Animations general;
    public Projectile(Handler handler, float x, float y, int width, int height,boolean mainplayer) {
        super(handler, x, y, width, height);
        this.mainplayer=mainplayer;
    }

    public void Movement()
    {
        if(direction == 0)
        {
            xMove=speed;
        }
        else if(direction == 1)
        {
            xMove=-speed;
        }
        else if(direction == 2)
        {
            yMove=speed;
        }
        else if(direction == 3)
        {
            yMove=-speed;
        }
        Move();
    }
    @Override
    public boolean NoCollisionWithEntities(float xOffset, float yOffset)
    {
        for (Entity entity: handler.getWorld().getEntityManager().getEntities())
        {
            if(!entity.equals(this)) {
                if (entity.getHitBox(0f, 0f).intersects(getHitBox(xOffset, yOffset))) //daca hitbox-urile entitatilor se ating de hitbox-ul + offset al entitatii curente
                {
                    if(entity.getHitable()) {
                        if((mainplayer==true && entity.getMainplayer()==false) || (mainplayer==false && entity.getMainplayer()==true))
                            if (entity.getDefence() - this.damage < 0)
                                entity.setHealth(entity.getHealth() - (this.damage - entity.getDefence()));
                    }
                    this.active=false;
                    return false; //va fi coliziune deci false
                }
            }
        }
        return true;
    }

    public void Move(){
        if(NoCollisionWithEntities(xMove, 0f)) //will it touch another's hitbox?
            moveX();
        if(NoCollisionWithEntities(0f, yMove)) //will it touch another's hitbox?
            moveY();
    }
    @Override
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
                active=false;
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
                active=false;
            }
        }

    }
    @Override
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
                active=false;
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
                active=false;
            }
        }
    }

}
