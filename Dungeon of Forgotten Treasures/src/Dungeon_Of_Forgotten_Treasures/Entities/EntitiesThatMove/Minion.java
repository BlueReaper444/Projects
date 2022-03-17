package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Graphics.Animations;
import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;

public class Minion extends Enemy {

    private Handler handler;
    private Animations WalkDown;
    private Animations WalkUp;
    private Animations WalkLeft;
    private Animations WalkRight;
    private Animations spotted;
    private Animations unspotted;

    public Minion(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 45+24, 66+33, moveType);
        this.handler = handler;
        health = 25;
        max_health = 25;
        general = new Animations(90, Assets.minion);
        speed = 3;
        hitbox.x =16;
        hitbox.y = 16;
        hitbox.width = 34;
        hitbox.height = 50;
        damage = 6;
        defense = 1;
        name = "Stolen Minion";
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
        handler.getWorld().getEntityManager().getPlayer().gainExp(35);
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+35);
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
}