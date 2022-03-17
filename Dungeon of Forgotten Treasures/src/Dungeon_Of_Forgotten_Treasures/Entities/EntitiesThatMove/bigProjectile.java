package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Graphics.Animations;
import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;


public class bigProjectile extends Projectile{
    private Handler handler;
    public bigProjectile(Handler handler, float x, float y, boolean moveType, short direction,int damage,boolean mainplayer) {
        super(handler, x, y, 55, 55,mainplayer);
        this.handler = handler;
        speed = 10;
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = width;
        hitbox.height = height;
        this.damage = damage;
        this.direction=direction;
        general = new Animations(90, Assets.bigBullet);
    }
    @Override
    public void GetKilled() {
        active = false;
    }

    @Override
    public void Update() {
        general.Update();
        Movement();
    }

    @Override
    public void Draw(Graphics graphics) {
        graphics.drawImage(general.getCurrentFrame(), (int)(x-handler.getGameCamera().getxOffset()),  (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
        //graphics.setColor(Color.red);
        //graphics.fillRect((int)(x + hitbox.x - handler.getGameCamera().getxOffset()), (int)(y + hitbox.y - handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);

    }

    public double getHitboxX()
    {
        return hitbox.x;
    }
    public double getHitboxY()
    {
        return hitbox.y;
    }
    public int getWidth()
    {
        return hitbox.width;
    }
    public int getHeight()
    {
        return hitbox.height;
    }
}
