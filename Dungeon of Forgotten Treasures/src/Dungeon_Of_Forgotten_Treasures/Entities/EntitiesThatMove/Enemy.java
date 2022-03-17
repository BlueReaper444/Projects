package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Entities.Factories.ProjectileEntityType;
import Dungeon_Of_Forgotten_Treasures.Graphics.Animations;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;

import java.awt.*;
import java.time.Clock;
import java.util.Calendar;

public abstract class Enemy extends MovingEntity {

    private Handler handler;
    protected long oldTime, timer; //timers pt miscare
    protected boolean moveType; //true = <---->  false = ^ v
    protected boolean attacking; //true = <---->  false = ^ v
    protected String name;
    protected int damage;
    protected Animations general;
    protected long attackTime;
    protected boolean spotedAnimation;
    protected boolean unspotedAnimation;

    public Enemy(Handler handler, float x, float y, int width, int height, boolean moveType) {
        super(handler, x, y, width, height);
        this.moveType = moveType;
        this.handler = handler;
        timer = 0;
        oldTime = System.currentTimeMillis();
        name = "Default Monster";
        damage = 1;
        attacking = false;
        attackTime = System.nanoTime();
        spotedAnimation=false;
        unspotedAnimation=false;
    }


    public void Movement()
    {
        int Tile = 64;
        xMove = 0;
        yMove = 0;
        timer += System.currentTimeMillis() - oldTime; //timer gets the time since the last update got called
        oldTime = System.currentTimeMillis();
        attacking = EnemySpoted();
        if(attacking == false) {
            if (moveType) //<--->
            {
                if (timer < 1500) {
                    xMove += speed;
                    Move();
                } else if (timer > 1500 && timer < 3000) {
                    xMove -= speed;
                    Move();
                } else if (timer > 3000) {
                    timer = 0;
                }
            } else {
                if (timer < 1500) {
                    yMove += speed;
                    Move();
                } else if (timer > 1500 && timer < 3000) {
                    yMove -= speed;
                    Move();
                } else if (timer > 3000) {
                    timer = 0;
                }
            }
        }
        else{
            float playerX = handler.getWorld().getPlayerX();
            float playerY = handler.getWorld().getPlayerY();
            boolean ok=false;//sus - jos axa y
            if(Math.abs(playerX -this.x) < Math.abs(playerY -this.y)){ //alege axa pe care ajunge mai repede in dreptul jucatorului
                ok=true; //stanga - dreapta axa x
            }
            if(ok==false)
            {
                if(Math.abs(playerY -this.y)>speed+1) { //alege dintre stanga si dreapta
                    if (playerY < this.y) {
                        yMove -= speed;
                    } else
                        yMove += speed;
                    if(Math.abs(playerY -this.y) <40)
                    {
                        AttackX(playerX); //atac pentru cand monstri se afla langa perete
                    }
                }
                else
                    AttackX(playerX); //daca nu e nevoie sa se miste ataca
                if(playerX > this.x - (15 * Tile)&& playerX < this.x - (5 * Tile)) //daca jucatorul se departeaza la mai mult de 5 tiles de inamic, acesta se apropie de el si pe axa y
                {
                    xMove -= speed;
                }
                else if(playerX < this.x + (15 * Tile) && playerX > this.x + (5 * Tile))
                {
                    xMove += speed;
                }
            }
            else
            {
                if(Math.abs(playerX -this.x)>speed+1) { //alege dintre sus is jos
                    if (playerX < this.x) {
                        xMove -= speed;
                    } else
                        xMove += speed;
                    if(Math.abs(playerY -this.y) <40)
                    {
                        AttackY(playerY); //atac pentru cand monstri se afla langa perete
                    }
                }
                else
                    AttackY(playerY);//daca nu e nevoie sa se miste ataca
                if(playerY > this.y - (15 * Tile)&& playerY < this.y - (5 * Tile)) //daca jucatorul se departeaza la mai mult de 5 tiles de inamic, acesta se apropie de el si pe axa x
                {
                    yMove -= speed;
                }
                else if(playerY < this.y + (15 * Tile) && playerY > this.y + (5 * Tile))
                {
                    yMove += speed;
                }
            }
            Move();
        }
    }
    protected void AttackX(float PLayerX){
        if(System.nanoTime()-attackTime>=1500000000) {
            if (x > PLayerX)
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+hitbox.x-30-1, y+height/2, true, ProjectileEntityType.SMALLPROJECTILE, (short) 1,damage,false)); //stanga
            else
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+hitbox.x+hitbox.width+1, y+height/2, true, ProjectileEntityType.SMALLPROJECTILE, (short) 0,damage,false)); //dreapta
            attackTime = System.nanoTime();
            handler.getWorld().playSE(1);
        }
    }

    protected void AttackY(float PLayerY){
        if(System.nanoTime()-attackTime>=1500000000) {
            if (y > PLayerY)
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+width/3, y+hitbox.y-20-speed-1, true, ProjectileEntityType.SMALLPROJECTILE, (short) 3,damage,false)); //jos
            else
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+width/3, y+hitbox.y+hitbox.height+speed+1, true, ProjectileEntityType.SMALLPROJECTILE, (short) 2,damage,false)); //sus
            attackTime = System.nanoTime();
            handler.getWorld().playSE(1);
        }
    }

    public boolean EnemySpoted() {
        int Tile = 64;
        float playerX = handler.getWorld().getPlayerX();
        float playerY = handler.getWorld().getPlayerY();
        if (this.attacking == false) {
            if (((playerX + (7 * Tile) >= this.x && playerX < this.x) || (playerX - (7 * Tile) <= this.x && playerX > this.x)) && ((playerY + (7 * Tile) >= this.y && playerY < this.y) || (playerY - (7 * Tile) <= this.y && playerY > this.y))) { //spoted x
                spotedAnimation = true;
                return true;
            } else
                return false;
        }

        if (this.attacking == true) {
            if (((playerX + (14 * Tile) <= this.x && playerX < this.x) || (playerX - (14 * Tile) >= this.x && playerX > this.x)) && ((playerY + (14 * Tile) <= this.y && playerY < this.y) || (playerY - (14 * Tile) >= this.y && playerY > this.y))) { //out of sight x
                unspotedAnimation = true;
                return false;
            } else
                return true;
        }
        return false;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public long getOldTime() {
        return oldTime;
    }

    public void setOldTime(long oldTime) {
        this.oldTime = oldTime;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public boolean isMoveType() {
        return moveType;
    }

    public void setMoveType(boolean moveType) {
        this.moveType = moveType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Animations getGeneral() {
        return general;
    }

    public void setGeneral(Animations general) {
        this.general = general;
    }
}
