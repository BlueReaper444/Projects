package Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove;

import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.MovingEntityType;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.ProjectileEntityType;
import Dungeon_Of_Forgotten_Treasures.Graphics.Animations;
import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.StringDrawer;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Inventory.InventoryAndStats;
import Dungeon_Of_Forgotten_Treasures.States.State;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovingEntity {

    //STATS
    private int mana;
    private int max_mana;
    private int intelligence;


    //Animations
    private Animations WalkDown;
    private Animations WalkUp;
    private Animations WalkLeft;
    private Animations WalkRight;
    private Animations levelup;
    private int inLastMove;
    private int score;
    private int exp;
    private int level;
    private int exp_cap;
    private int damage;
    private InventoryAndStats playerInventoryAndStats;
    private static Player self;
    private boolean okAttack;
    private int baseDamage;
    private long manaChargeTime;
    private boolean justLeveledUp;

    //SINGLETON.
    private Player(Handler handler, float x, float y, int speed) {
        super(handler, x, y, MovingEntity.DEFAULT_CREATURE_WIDTH, MovingEntity.DEFAULT_CREATURE_HEIGHT);
        this.speed = speed;
        hitbox.x =16;
        hitbox.y = 16;
        hitbox.width = 34;
        hitbox.height = 50;
        mainplayer=true;
        levelup = new Animations(30, Assets.levelup);
        //Animations for walking
        WalkDown = new Animations(250, Assets.player_down);//250 miliseconds
        WalkUp = new Animations(250, Assets.player_up);
        WalkLeft = new Animations(250, Assets.player_left);
        WalkRight = new Animations(250, Assets.player_right);


        inLastMove=4; //start facing down.
        playerInventoryAndStats = InventoryAndStats.getSelf(handler);

        //Stats

        max_health = 40;
        health = 40;
        max_mana = 20;
        mana = 20;
        intelligence = 1;
        defense = 0;

        score = 0;
        level = 1;
        exp = 0;
        exp_cap = 100;
        baseDamage = 6;
        damage=baseDamage*intelligence;
        defence=2;
        okAttack=true;
        hitable=true;
        manaChargeTime = System.nanoTime();
        justLeveledUp=false;
    }

    public static Player getSelf(Handler handler, float x, float y, int speed)
    {
        if (self == null)
            self = new Player(handler, x, y, speed);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void gainExp(int exp)
    {
        this.exp += exp;
        if (this.exp >= exp_cap) //level up
        {
            level++;
            this.exp = this.exp - exp_cap;
            max_health+= 4;
            health+= 4;
            max_mana+= 2;
            mana+= 2;
            score+= level*200;
            exp_cap += exp_cap;
            defense += 1;
            intelligence += 1;
            damage=baseDamage*intelligence;
            justLeveledUp=true;
            handler.getWorld().playSE(5);
        }
    }

    public void restoreMana(int amount)
    {
        mana += amount;
        if (mana > max_mana)
            mana = max_mana;
        else if (mana < 0)
            mana = 0;
    }

    private void Actions() {
        xMove = 0;
        yMove = 0;
        //very important that they get reset;
        if (!playerInventoryAndStats.isActive()) {
            if (handler.getKeyManager().up) {
                yMove = -speed;

            }
            if (handler.getKeyManager().down) {
                yMove = speed;
            }
            if (handler.getKeyManager().left) {
                xMove = -speed;
            }
            if (handler.getKeyManager().right) {
                xMove = speed;
            }
            if (handler.getKeyManager().exit_to_menu) {
                State.setCurrentState(handler.getGame().getMenuState());
            }
            if (handler.getKeyManager().take) {
                itemTake();
            }
            else {
                okAttack = true;
            }
        }
    }

    private void itemTake() {
        Rectangle player_hitbox = getHitBox(0, 0); //hitbox player
        Rectangle take_range = new Rectangle();
        int trSize = 20;
        take_range.width = trSize;
        take_range.height = trSize;
        switch (inLastMove) {
            case 1: { //left
                take_range.x = player_hitbox.x - trSize;
                take_range.y = player_hitbox.y + player_hitbox.height / 2 - trSize / 2;
                break;
            }
            case 2: { //right
                take_range.x = player_hitbox.x + player_hitbox.width;
                take_range.y = player_hitbox.y + player_hitbox.height / 2 - trSize / 2;
                break;
            }
            case 3: { //up
                take_range.x = player_hitbox.x + player_hitbox.width / 2 - trSize / 2;
                take_range.y = player_hitbox.y - trSize;
                break;
            }
            case 4: { //down
                take_range.x = player_hitbox.x + player_hitbox.width / 2 - trSize / 2;
                take_range.y = player_hitbox.y + player_hitbox.height;
                break;
            }
            default:
                return;
        }

        for (Entity entity : handler.getWorld().getEntityManager().getEntities()) {
            if (!entity.equals(this)&&entity.getHitable()==false) //if it's not the player
            {
                if (entity.getHitBox(0, 0).intersects(take_range)) {
                    okAttack=false;
                    entity.GetKilled(); //"kill" the entity, only should happen to items to pick them up
                    return;
                }
            }
        }
        if (okAttack == true && mana > 0) {
            //handler.getWorld().getEntityManager().addEntity(handler.getWorld().getMoving().MakeMovingEntity(handler, x + 150, y, true, MovingEntityType.SHADOW, (short) 0, 0));
            switch (inLastMove) {
                case 1: //stanga
                    handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x + hitbox.x - 30 - 1, y + height / 2, true, ProjectileEntityType.SMALLPROJECTILE, (short) 1, damage, true));
                    --mana; //daca nu ai coliziune, folosesti magie linistit.
                    break;
                case 2: //dreapta
                    handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x + hitbox.x + hitbox.width + 1, y + height / 2, true, ProjectileEntityType.SMALLPROJECTILE, (short) 0, damage, true));
                    --mana;
                    break;
                case 3: //jos
                    handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x + width / 3, y + hitbox.y - 20 - speed - 1, true, ProjectileEntityType.SMALLPROJECTILE, (short) 3, damage, true));
                    --mana;
                    break;
                default: //sus
                    handler.getWorld().getProjectileManager().addProjectile(handler.getWorld().getProjectile().MakeProjectileEntity(handler, x + width / 3, y + hitbox.y + hitbox.height + speed + 1, true, ProjectileEntityType.SMALLPROJECTILE, (short) 2, damage, true));
                    --mana;
                    break;
            }
            handler.getWorld().playSE(1);
            okAttack = false;
        }


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

    private BufferedImage getCurrentAnimationFrame()
    {

        if(xMove<0){//moving left
            inLastMove=1;
            return WalkLeft.getCurrentFrame();
        }
        else if(xMove>0)//moving right
        {
            inLastMove=2;
            return WalkRight.getCurrentFrame();
        }
        else if(yMove<0)//moving up
        {
            inLastMove=3;
            return WalkUp.getCurrentFrame();
        }
        else if(yMove>0)//moving down
        {
            inLastMove=4;
            return WalkDown.getCurrentFrame();
        }
        else//stopped moving
        {
            switch(inLastMove){
                case 1: return Assets.playeridle_left;
                case 2: return Assets.playeridle_right;
                case 3: return Assets.playeridle_up;
                default: return Assets.playeridle_down;
            }
        }

    }

    @Override
    public void GetKilled()
    {
        active = false; //mark for manager to remove
        System.out.println("You died.\nFinal score: " + handler.getWorld().getEntityManager().getPlayer().getScore());
        //System.exit(0);
        State.setCurrentState(handler.getGame().getEndgameState());
    }

    @Override
    public void Update()
    {
        //Animation
        if(justLeveledUp==true) {
            levelup.Update();
        }
        WalkDown.Update();
        WalkUp.Update();
        WalkLeft.Update();
        WalkRight.Update();
        //Movement & Actions
        Actions();
        Move();
        //Camera
        handler.getGameCamera().FocusEntity(this);
        //we want the camera to center on player
        //Inventory+Stats
        playerInventoryAndStats.Update();
        if(health<=0)
            GetKilled();
        if(System.nanoTime()-manaChargeTime>=2000000000) {
            if(mana<max_mana)
                ++mana;
            manaChargeTime=System.nanoTime();
        }
    }
    @Override
    public void Draw(Graphics graphics)
    {
        if(justLeveledUp==true) {
            graphics.drawImage(levelup.getCurrentFrame(), (int) (x + hitbox.x - handler.getGameCamera().getxOffset() + 7), (int) (y - handler.getGameCamera().getyOffset() - levelup.getCurrentFrame().getHeight()), 24, levelup.getCurrentFrame().getHeight(), null);
            if(levelup.getIndex()==7)
            {
                justLeveledUp=false;
                levelup.setIndex(0);
            }
        }
        graphics.drawImage(getCurrentAnimationFrame(), (int)(x- handler.getGameCamera().getxOffset()),  (int)(y- handler.getGameCamera().getyOffset()), width, height, null);

        //Hp Bar
        StringDrawer.StringDraw(graphics, "HP", 50, 30, Color.white, Assets.font36);
        graphics.setColor(Color.GRAY);
        graphics.fillRect(50,30,max_health,30);
        if(health < (max_health/4))
            graphics.setColor(Color.RED);
        else
            graphics.setColor(Color.GREEN);
        graphics.fillRect(50,30,health,30);

        //Mana bar
        StringDrawer.StringDraw(graphics, "MANA", 50, 90, Color.white, Assets.font36);
        graphics.setColor(Color.GRAY);
        graphics.fillRect(50,90,max_mana*2,30);
        if(mana < (max_mana/4))
            graphics.setColor(Color.RED);
        else
            graphics.setColor(Color.BLUE);
        graphics.fillRect(50,90,mana*2,30);
    }

    public void drawInventoryAndStats(Graphics graphics)
    {
        playerInventoryAndStats.Draw(graphics);
    }
    public InventoryAndStats getPlayerInventoryAndStats() {
        return playerInventoryAndStats;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMax_mana() {
        return max_mana;
    }

    public void setMax_mana(int max_mana) {
        this.max_mana = max_mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp_cap() {
        return exp_cap;
    }

    public void setExp_cap(int exp_cap) {
        this.exp_cap = exp_cap;
    }


}