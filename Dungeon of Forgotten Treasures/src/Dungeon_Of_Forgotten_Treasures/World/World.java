package Dungeon_Of_Forgotten_Treasures.World;

import Dungeon_Of_Forgotten_Treasures.Entities.Entity;
import Dungeon_Of_Forgotten_Treasures.Entities.EntityManager;
import Dungeon_Of_Forgotten_Treasures.Entities.EntitiesThatMove.Player;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.EntityFactory;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.MovingEntityFactory;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.ProjectileFactory;
import Dungeon_Of_Forgotten_Treasures.Entities.Factories.StaticEntityFactory;
import Dungeon_Of_Forgotten_Treasures.Entities.ProjectileManager;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.Light;
import Dungeon_Of_Forgotten_Treasures.Sound;
import Dungeon_Of_Forgotten_Treasures.States.State;
import Dungeon_Of_Forgotten_Treasures.Tiles.Tile;
import Dungeon_Of_Forgotten_Treasures.Utility.Utility;
import Dungeon_Of_Forgotten_Treasures.World.EntityPlacement.WorldEntities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static Dungeon_Of_Forgotten_Treasures.Tiles.Tile.TILEHEIGHT;
import static Dungeon_Of_Forgotten_Treasures.Tiles.Tile.TILEWIDTH;

public class World {

    private Handler handler;
    private int width; //map size
    private int height; //^
    private int player_spawnX; //spawn point for player
    private int player_spawnY; //^
    private int[][] map; //The map of the game
    private int mapNumber; //this is for the player only; bear with me..

    //Entities
    private EntityManager entityManager; ///<All the entities in a ArrayList
    private ProjectileManager projectileManager; ///<All the entities in a ArrayList
    private static EntityFactory Moving;
    private static EntityFactory Staying;
    private static EntityFactory Projectile;
    private boolean mapEntities; //folositor sa stie cand sa incarce entitati noi AKA cand se schimba harta
    private Sound worldSound= new Sound();

    //Singleton
    private static World self;

    //Lights
    private List<Light> lights;
    private BufferedImage lightMap;



    private World(Handler handler, String path)
    {
        lights=new ArrayList<Light>();
        lights.add(new Light(32*width, 32*height, 1,0));

        this.handler = handler;
        entityManager = EntityManager.getSelf(handler, Player.getSelf(handler, 100, 100, 6));
        loadWorld(path);
        projectileManager = projectileManager.getSelf(handler, Player.getSelf(handler, 100, 100, 6));
        Staying = new StaticEntityFactory();
        Moving = new MovingEntityFactory();
        Projectile = new ProjectileFactory();
        WorldEntities.map1Entities(handler, entityManager, Staying, Moving);
        entityManager.getPlayer().setX(player_spawnX);
        entityManager.getPlayer().setY(player_spawnY);
        mapNumber = 2; //incepand cu a doua camera; prima se incarca by default
        mapEntities = false;
        worldSound = new Sound();
    }

    public static World getSelf(Handler handler, String path)
    {
        if (self == null)
            self = new World(handler, path);
        return self;
    }

    public static void reset()
    {
        self = null;
    }


    //loads a world from a file (matrix). adds entities.
    private void loadWorld(String path)
    {
        String file = Utility.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); //separa nr cu space
        width = Utility.parseInt(tokens[0]);
        height = Utility.parseInt(tokens[1]);
        player_spawnX = Utility.parseInt(tokens[2]);
        player_spawnY = Utility.parseInt(tokens[3]);


        map = new int[width][height];

        for (int y=0; y<height; y++)
        {
            for (int x=0; x<width; x++)
            {
                map[x][y] = Utility.parseInt(tokens[(y*width + x)+4]);
                //vectorul in sine e "inversat" (latimea e prima, inaltimea a doua)
                //dar la desenat merge ok, ptc avem x si y in pozitiile bune
            }
        }

        playMusic(0);
        //Light();

    }

    //helpful for changing maps. deletes all entities from current map except player
    public void loadAWorld(String path)
    {

        //mergem pe alta harta, deci toate entitatile de pe harta curenta dispar
        for (int i = 0; i< entityManager.getEntities().size(); i++)
        {
            Entity entity = entityManager.getEntities().get(i);
            if (!entity.equals(entityManager.getPlayer()))  //clear all entities that aren't the player
                entity.setActive(false);
        }
        for (int i = 0; i< projectileManager.getEntities().size(); i++)
        {
            Entity entity = projectileManager.getEntities().get(i);
                entity.setActive(false);
        }
        mapEntities = true;
        map = null; //bye bye old map
        loadWorld(path); //hello new map
        Player.getSelf(handler, 100, 100, 6).setX(player_spawnX); //setezi spawn-ul
        Player.getSelf(handler, 100, 100, 6).setY(player_spawnY); //^
        //Light();
    }


    public void Update()
    {
        entityManager.Update();
        //if(!projectileManager.getEntities().isEmpty())
        projectileManager.Update();
        if (mapEntities) //trebuie asa neaparat, altfel va da exceptie ConcurrentModificationException
        {
            mapEntities = false;
            switch(mapNumber)
            {
                case 2: WorldEntities.map2Entities(handler, entityManager, Staying, Moving);
                    break;
                case 3: WorldEntities.map3Entities(handler, entityManager, Staying, Moving);
                    playSE(2);
                    break;
                case 4: WorldEntities.map4Entities(handler, entityManager, Staying, Moving);
                    break;
                case 5: WorldEntities.map5Entities(handler, entityManager, Staying, Moving);
                    break;
                case 6: WorldEntities.map6Entities(handler, entityManager, Staying, Moving);
                    break;
                default: break;
            }

            mapNumber = mapNumber + 1;

        }
    }

    public void Draw(Graphics graphics)
    {
        //we don't want xStart <0, nor yStart bigger than Map size
        //0 = cea mai stanga pozitie posibila adica tile (0,0)
        int xStart = Math.max(0 , (int) handler.getGameCamera().getxOffset() / TILEWIDTH); //the up left corner of camera screen
        int xEnd = Math.min(width, (int)((handler.getGameCamera().getxOffset() + handler.getWidth())/ Tile.TILEWIDTH + 1)); //the up right corner of camera screen
        int yStart = Math.max(0 , (int) handler.getGameCamera().getyOffset() / TILEHEIGHT); //the down left corner of camera screen
        int yEnd = Math.min(height, (int)((handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT + 1)); //the down right corner of camera screen
        //this is for efficiency sake. so you don't try to render EVERYTHING EVERYTIME


        for(int y=yStart; y< yEnd; y++)
            for(int x=xStart; x< xEnd; x++)
            {
                getTile(x,y).Draw(graphics, (int)(Tile.TILEWIDTH*x - handler.getGameCamera().getxOffset()) , (int)(Tile.TILEHEIGHT*y - handler.getGameCamera().getyOffset()));
                //THIS IS HOW IT SHOULD WORK.
            }
        //Entities
        entityManager.Draw(graphics);

        graphics.drawImage(lightMap, (int)(0-handler.getGameCamera().getxOffset()),  (int)(0-handler.getGameCamera().getyOffset()), 64*width, 64*height, null);
        projectileManager.Draw(graphics);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getPlayer_spawnX() {
        return player_spawnX;
    }

    public int getPlayer_spawnY() {
        return player_spawnY;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static EntityFactory getMoving() {
        return Moving;
    }

    public static void setMoving(EntityFactory moving) {
        Moving = moving;
    }

    public static EntityFactory getStaying() {
        return Staying;
    }

    public static void setStaying(EntityFactory staying) {
        Staying = staying;
    }

    public static EntityFactory getProjectile() {
        return Projectile;
    }

    public static void setProjectile(EntityFactory projectile) {
        Projectile = projectile;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public Tile getTile(int x, int y)
    {
        //if the player gets out of the map..
        if(x< 0 || y < 0 || x >=width || y>=height)
            return Tile.floorTile;
        Tile t = Tile.tiles[map[x][y]];
        if (t == null)
            return Tile.floorTile;
        return t;
    }

    public float getPlayerX() {
        return entityManager.getPlayer().getX();
    }

    public float getPlayerY() {
        return entityManager.getPlayer().getY();
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }
    public void setProjectileManager(ProjectileManager projectileManager) {
        this.projectileManager = projectileManager;
    }

    public void playMusic(int soundIndex)
    {
        worldSound.setFile(soundIndex);
        worldSound.play();
        worldSound.loop();
    }
    public void stopMusic(){
        worldSound.stop();
    }

    public void playSE(int soundIndex)
    {
        worldSound.setFile(soundIndex);
        worldSound.play();
    }

    public void Light()
    {
        lightMap=new BufferedImage(64*width,64*height,BufferedImage.TYPE_INT_ARGB);

        lights.clear();
        System.out.println(mapNumber);
        switch(mapNumber)
        {
            case 1:

                break;
            case 2:
                lights.add(new Light(64 * 5, 64 * 22, 100,5));
                System.out.println("AICI");
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                lights.add(new Light(64 * 4, 64 * 2, 200,5));
                lights.add(new Light(64 * 7, 64 * 2, 200,5));
                lights.add(new Light(64 * 10, 64 * 2, 200,5));
                lights.add(new Light(64 * 2, 64 * 5, 200,5));
                lights.add(new Light(64 * 2, 64 * 9, 200,5));
                break;
        }
        Graphics2D g2d = (Graphics2D) lightMap.getGraphics();
        g2d.setColor(new Color(0,0,0,200));

        g2d.fillRect(0,0,64*width,64*height);
        g2d.setComposite(AlphaComposite.DstOut);
            for(int i=0;i<lights.size();++i)
            {
                lights.get(i).render(g2d);
            }
        g2d.dispose();
    }

    public void addLights(Light light) {
        lights.add(light);
    }

    public List<Light> getLights() {
        return lights;
    }
}