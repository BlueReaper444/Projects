package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Entities.Factories.ProjectileEntityType;
import Dungeon_Of_Forgotten_Treasures.Graphics.Animations;
import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;

public class Shadow extends Enemy {

    private Handler handler;
    private Animations WalkDown;
    private Animations WalkUp;
    private Animations WalkLeft;
    private Animations WalkRight;
    private Animations spotted;
    private Animations unspotted;

    public Shadow(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 120, 105, moveType);
        this.handler = handler;
        health = 50;
        max_health = 50;
        general = new Animations(60, Assets.shadow);
        speed = 4;
        hitbox.x =35;
        hitbox.y = 20;
        hitbox.width = 40;
        hitbox.height = 75;
        damage = 20;
        defense = 1;
        name = "Shadow Puppet";
        spotted = new Animations(120, Assets.spotted);
        unspotted= new Animations(120, Assets.unspotted);

        //WalkDown = new Animations(250, Assets.player_down);//250 miliseconds
        //WalkUp = new Animations(250, Assets.player_up);
        //WalkLeft = new Animations(250, Assets.player_left);
        //WalkRight = new Animations(250, Assets.player_right);
    }

    @Override
    public void GetKilled() {
        active = false;
        handler.getWorld().getEntityManager().getPlayer().gainExp(100);
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+100);
    }

    @Override
    public void Update() {
        if(spotedAnimation==true) {
            spotted.Update();
        }
        if(unspotedAnimation==true) {
            unspotted.Update();
        }
        general.Update();
        if(health<=0)
            GetKilled();
        Movement();
    }

    @Override
    public void Draw(Graphics graphics) {
        if(spotedAnimation==true) {
            graphics.drawImage(spotted.getCurrentFrame(), (int) (x + hitbox.x - handler.getGameCamera().getxOffset() + 7), (int) (y - handler.getGameCamera().getyOffset() - spotted.getCurrentFrame().getHeight()-spotted.getIndex()), spotted.getCurrentFrame().getWidth(), spotted.getCurrentFrame().getHeight(), null);
            if(spotted.getIndex()==7)
            {
                spotedAnimation=false;
                spotted.setIndex(0);
            }
        }
        if(unspotedAnimation==true) {
            graphics.drawImage(unspotted.getCurrentFrame(), (int) (x + hitbox.x - handler.getGameCamera().getxOffset() + 7), (int) (y - handler.getGameCamera().getyOffset() - unspotted.getCurrentFrame().getHeight()-unspotted.getIndex()), unspotted.getCurrentFrame().getWidth(), unspotted.getCurrentFrame().getHeight(), null);
            if(unspotted.getIndex()==7)
            {
                unspotedAnimation=false;
                unspotted.setIndex(0);
            }
        }
        graphics.drawImage(general.getCurrentFrame(), (int)(x-handler.getGameCamera().getxOffset()),  (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
        //graphics.setColor(Color.red);
        //graphics.fillRect((int)(x + hitbox.x - handler.getGameCamera().getxOffset()), (int)(y + hitbox.y - handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);
    }

    @Override
    protected void AttackX(float PLayerX){
        if(System.nanoTime()-attackTime>=1500000000) {
            if (x > PLayerX)
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+hitbox.x-30-1, y+height/2, true, ProjectileEntityType.MEDIUMPROJECTILE, (short) 1,damage,false)); //stanga
            else
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+hitbox.x+hitbox.width+1, y+height/2, true, ProjectileEntityType.MEDIUMPROJECTILE, (short) 0,damage,false)); //dreapta
            attackTime = System.nanoTime();
            handler.getWorld().playSE(1);
        }
    }
    @Override
    protected void AttackY(float PLayerY){
        if(System.nanoTime()-attackTime>=1500000000) {
            if (y > PLayerY)
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+width/3, y+hitbox.y-35-speed-1, true, ProjectileEntityType.MEDIUMPROJECTILE, (short) 3,damage,false)); //jos
            else
                handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x+width/3, y+hitbox.y+hitbox.height+speed+1, true, ProjectileEntityType.MEDIUMPROJECTILE, (short) 2,damage,false)); //sus
            attackTime = System.nanoTime();
            handler.getWorld().playSE(1);
        }
    }
}
