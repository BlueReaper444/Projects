package Dungeon_Of_Forgotten_Treasures.Graphics;

import Dungeon_Of_Forgotten_Treasures.Graphics.Factory.SpriteSheetFactory;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.ImageLoader;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.StringDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static SpriteSheetFactory factory;
    public static BufferedImage ground_1, ground_2, ground_3, ground_stairs, water_tile;
    public static BufferedImage lava_1, lava_2, lava_3;
    public static BufferedImage graywall_1, graywall_2, graywall_3,graywall_4,graywall_5;
    public static BufferedImage darkwall_1, darkwall_2, darkwall_3,darkwall_4,darkwall_5;
    public static BufferedImage door_1, door_2;

    public static BufferedImage health_potion;
    public static BufferedImage health_potion_better;
    public static BufferedImage max_health_potion;
    public static BufferedImage mana_potion;
    public static BufferedImage mana_potion_better;
    public static BufferedImage max_mana_potion;

    public static BufferedImage playeridle_up, playeridle_down, playeridle_left, playeridle_right;
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;

    public static BufferedImage[] horned_mage;
    public static BufferedImage[] shadow;
    public static BufferedImage[] minion;
    public static BufferedImage[] tentacle;
    public static BufferedImage[] bigBullet;
    public static BufferedImage[] boss1;
    public static BufferedImage[] boss2;
    public static BufferedImage[] boss3;

    public static BufferedImage[] blue_flame;
    public static BufferedImage background, bg_top, bg_bottom;
    public static Font font36;
    public static Font font64;
    public static Font font72;

    public static BufferedImage[] levelup;
    public static BufferedImage[] spotted;
    public static BufferedImage[] unspotted;


    private static final int tileWidth = 32; //default sizes for 1 image
    private static final int tileHeight = 32;

    public static void init()
    {
        factory = new SpriteSheetFactory();
        SpriteSheet sheetPlayer = factory.makeSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet sheetGrounds = factory.makeSheet(ImageLoader.loadImage("/textures/dg_grounds32.gif"));
        SpriteSheet sheetWalls = factory.makeSheet(ImageLoader.loadImage("/textures/dg_dungeon32.gif"));
        SpriteSheet sheetWalls1 = factory.makeSheet(ImageLoader.loadImage("/textures/dg_brick_wall_gray_32.png"));
        SpriteSheet sheetWalls2 = factory.makeSheet(ImageLoader.loadImage("/textures/dg_brick_wall_dark_32.png"));
        SpriteSheet sheetPotions = factory.makeSheet(ImageLoader.loadImage("/textures/potions_dungeon.png"));
        SpriteSheet sheetBlueFlame = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/flameball-32x32.png"));
        SpriteSheet features = factory.makeSheet(ImageLoader.loadImage("/textures/dg_features32.gif"));

        //Monsters
        SpriteSheet sheetHornedMonster = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/gnu-120x100.png"));
        SpriteSheet sheetShadowMonster = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/shadow-80x70.png"));
        SpriteSheet sheetMinionMonster = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/minion-45x66.png"));
        SpriteSheet sheetTentacleMonster = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/tentacles-25x90.png"));
        SpriteSheet BossBullet = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/mage-bullet-13x13.png"));
        SpriteSheet sheetBoss1 = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/mage-1-85x94.png"));
        SpriteSheet sheetBoss2 = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/mage-2-122x110.png"));
        SpriteSheet sheetBoss3 = factory.makeSheet(ImageLoader.loadImage("/textures/Monsters/mage-3-87x110.png"));


        //UI:
        SpriteSheet[] sheetMenu = new SpriteSheet[1];
        sheetMenu[0] = factory.makeSheet(ImageLoader.loadImage("/textures/UI/background.jpg"));

        //Exclamation
        SpriteSheet[] exclamation = new SpriteSheet[3];
        exclamation[0] = factory.makeSheet(ImageLoader.loadImage("/textures/LevelUp.png"));
        exclamation[1] = factory.makeSheet(ImageLoader.loadImage("/textures/Spotted.png"));
        exclamation[2] = factory.makeSheet(ImageLoader.loadImage("/textures/Spotted2.png"));


        background = sheetMenu[0].crop(0, 0, 640, 480);
        bg_top =sheetMenu[0].crop(0, 0, 640, 240);
        bg_bottom =sheetMenu[0].crop(0, 240, 640, 240);

        font36 = StringDrawer.FontLoad("/textures/UI/alagard.ttf", 36);
        font64 = StringDrawer.FontLoad("/textures/UI/alagard.ttf", 64);
        font72 = StringDrawer.FontLoad("/textures/UI/alagard.ttf", 72);

        //Player:
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        playeridle_down = sheetPlayer.crop(tileWidth*4, tileHeight*4, tileWidth, tileHeight);
        player_down[0] = sheetPlayer.crop(tileWidth*3, tileHeight*4, tileWidth, tileHeight);
        player_down[1] = sheetPlayer.crop(tileWidth*5, tileHeight*4, tileWidth, tileHeight);

        playeridle_left = sheetPlayer.crop(tileWidth*4, tileHeight*5, tileWidth, tileHeight);
        player_left[0] = sheetPlayer.crop(tileWidth*3, tileHeight*5, tileWidth, tileHeight);
        player_left[1] = sheetPlayer.crop(tileWidth*5, tileHeight*5, tileWidth, tileHeight);

        playeridle_right = sheetPlayer.crop(tileWidth*4, tileHeight*6, tileWidth, tileHeight);
        player_right[0] = sheetPlayer.crop(tileWidth*3, tileHeight*6, tileWidth, tileHeight);
        player_right[1] = sheetPlayer.crop(tileWidth*5, tileHeight*6, tileWidth, tileHeight);

        playeridle_up = sheetPlayer.crop(tileWidth*4, tileHeight*7, tileWidth, tileHeight);
        player_up[0] = sheetPlayer.crop(tileWidth*3, tileHeight*7, tileWidth, tileHeight);
        player_up[1] = sheetPlayer.crop(tileWidth*5, tileHeight*7, tileWidth, tileHeight);

        //Potions
        health_potion = sheetPotions.crop(tileWidth*4, tileHeight, tileWidth, tileHeight);
        health_potion_better = sheetPotions.crop(0, tileHeight*7, tileWidth, tileHeight);
        max_health_potion = sheetPotions.crop(tileWidth*5, tileHeight*8, tileWidth, tileHeight);
        mana_potion = sheetPotions.crop(0, tileHeight, tileWidth, tileHeight);
        mana_potion_better = sheetPotions.crop(tileWidth*2, tileHeight*4, tileWidth, tileHeight);
        max_mana_potion = sheetPotions.crop(tileWidth*4, 0, tileWidth, tileHeight);

        //Effects
        blue_flame = new BufferedImage[4];
        blue_flame[0] = sheetBlueFlame.crop(0, 0, tileWidth, tileHeight);
        blue_flame[1] = sheetBlueFlame.crop(tileWidth, 0, tileWidth, tileHeight);
        blue_flame[2] = sheetBlueFlame.crop(tileWidth*2, 0, tileWidth, tileHeight);
        blue_flame[3] = sheetBlueFlame.crop(tileWidth*3, 0, tileWidth, tileHeight);

        tentacle = new BufferedImage[8];
        tentacle[0] = sheetTentacleMonster.crop(0, 2*90, 25, 90);
        tentacle[1] = sheetTentacleMonster.crop(25, 2*90, 25, 90);
        tentacle[2] = sheetTentacleMonster.crop(25*2, 2*90, 25, 90);
        tentacle[3] = sheetTentacleMonster.crop(25*3, 2*90, 25, 90);
        tentacle[4] = sheetTentacleMonster.crop(25*4, 2*90, 25, 90);
        tentacle[5] = sheetTentacleMonster.crop(25*5, 2*90, 25, 90);
        tentacle[6] = sheetTentacleMonster.crop(25*6, 2*90, 25, 90);
        tentacle[7] = sheetTentacleMonster.crop(25*7, 2*90, 25, 90);

        bigBullet = new BufferedImage[4];
        bigBullet[0] = BossBullet.crop(0, 00, 13, 13);
        bigBullet[1] = BossBullet.crop(13, 0, 13, 13);
        bigBullet[2] = BossBullet.crop(13*2, 0, 13, 13);
        bigBullet[3] = BossBullet.crop(13*3, 0, 13, 13);


        //Tiles

        ground_1 = sheetGrounds.crop(tileWidth*6, 0, tileWidth, tileHeight);
        ground_2 = sheetGrounds.crop(tileWidth*7, 0, tileWidth, tileHeight);
        ground_3 = sheetWalls2.crop(0, 0, tileWidth, tileHeight);
        ground_stairs = sheetWalls1.crop(tileWidth*2, 0, tileWidth, tileHeight);
        water_tile = features.crop(tileWidth*8, tileHeight*5, tileWidth, tileHeight);

        lava_1 = sheetGrounds.crop(tileWidth*6, tileHeight*3, tileWidth, tileHeight);
        lava_2 = sheetGrounds.crop(tileWidth*7, tileHeight*3, tileWidth, tileHeight);
        lava_3 = sheetGrounds.crop(tileWidth*8, tileHeight*3, tileWidth, tileHeight);

        graywall_1 = sheetWalls1.crop(tileWidth*1, tileHeight*2, tileWidth, tileHeight);
        graywall_2 = sheetWalls1.crop(tileWidth*0, tileHeight*1, tileWidth, tileHeight);
        graywall_3 = sheetWalls1.crop(tileWidth*2, tileHeight*1, tileWidth, tileHeight);
        graywall_4 = sheetWalls1.crop(tileWidth*1, tileHeight*0, tileWidth, tileHeight);
        graywall_5 = sheetWalls1.crop(tileWidth*1, tileHeight*1, tileWidth, tileHeight);

        darkwall_1 = sheetWalls2.crop(tileWidth*1, tileHeight*2, tileWidth, tileHeight);
        darkwall_2 = sheetWalls2.crop(tileWidth*0, tileHeight*1, tileWidth, tileHeight);
        darkwall_3 = sheetWalls2.crop(tileWidth*2, tileHeight*1, tileWidth, tileHeight);
        darkwall_4 = sheetWalls2.crop(tileWidth*1, tileHeight*0, tileWidth, tileHeight);
        darkwall_5 = sheetWalls2.crop(tileWidth*1, tileHeight*1, tileWidth, tileHeight);

        door_1 = sheetWalls.crop(3*tileWidth, tileHeight*3, tileWidth, tileHeight);
        door_2 = sheetWalls.crop(4*tileWidth, tileWidth*3, tileWidth, tileHeight);


        //Monsters
        horned_mage = new BufferedImage[20];
        horned_mage[0] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[1] = sheetHornedMonster.crop(120,0,120,100);
        horned_mage[2] = sheetHornedMonster.crop(120*2,0,120,100);
        horned_mage[3] = sheetHornedMonster.crop(120*3,0,120,100);
        horned_mage[4] = sheetHornedMonster.crop(120*4,0,120,100);
        horned_mage[5] = sheetHornedMonster.crop(0,100,120,100);
        horned_mage[6] = sheetHornedMonster.crop(120*4,0,120,100);
        horned_mage[7] = sheetHornedMonster.crop(120*3,0,120,100);
        horned_mage[8] = sheetHornedMonster.crop(120*2,0,120,100);
        horned_mage[9] = sheetHornedMonster.crop(120,0,120,100);
        horned_mage[10] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[11] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[12] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[13] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[14] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[15] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[16] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[17] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[18] = sheetHornedMonster.crop(0,0,120,100);
        horned_mage[19] = sheetHornedMonster.crop(0,0,120,100);

        shadow = new BufferedImage[4];
        shadow[0] = sheetShadowMonster.crop(0, 4*70, 80, 70);
        shadow[1] = sheetShadowMonster.crop(80, 4*70, 80, 70);
        shadow[2] = sheetShadowMonster.crop(80*2, 4*70, 80, 70);
        shadow[3] = sheetShadowMonster.crop(80*3, 4*70, 80, 70);

        minion = new BufferedImage[3];
        minion[0] = sheetMinionMonster.crop(0, 0, 45, 66);
        minion[1] = sheetMinionMonster.crop(45, 0, 45, 66);
        minion[2] = sheetMinionMonster.crop(90, 0, 45, 66);



        boss1 = new BufferedImage[8];
        boss1[0] = sheetBoss1.crop(0, 0, 85, 94);
        boss1[1] = sheetBoss1.crop(85, 0, 85, 94);
        boss1[2] = sheetBoss1.crop(85*2, 0, 85, 94);
        boss1[3] = sheetBoss1.crop(85*3, 0, 85, 94);
        boss1[4] = sheetBoss1.crop(0, 94, 85, 94);
        boss1[5] = sheetBoss1.crop(85, 94, 85, 94);
        boss1[6] = sheetBoss1.crop(85*2, 94, 85, 94);
        boss1[7] = sheetBoss1.crop(85*3, 94, 85, 94);

        boss2 = new BufferedImage[8];
        boss2[0] = sheetBoss2.crop(0, 0, 122, 110);
        boss2[1] = sheetBoss2.crop(122, 0, 122, 110);
        boss2[2] = sheetBoss2.crop(122*2, 0, 122, 110);
        boss2[3] = sheetBoss2.crop(122*3, 0, 122, 110);
        boss2[4] = sheetBoss2.crop(0, 110, 122, 110);
        boss2[5] = sheetBoss2.crop(122, 110, 122, 110);
        boss2[6] = sheetBoss2.crop(122*2, 110, 122, 110);
        boss2[7] = sheetBoss2.crop(122*3, 110, 122, 110);

        boss3 = new BufferedImage[8];
        boss3[0] = sheetBoss3.crop(0, 0, 87, 110);
        boss3[1] = sheetBoss3.crop(87, 0, 87, 110);
        boss3[2] = sheetBoss3.crop(87*2, 0, 87, 110);
        boss3[3] = sheetBoss3.crop(87*3, 0, 87, 110);
        boss3[4] = sheetBoss3.crop(0, 110, 87, 110);
        boss3[5] = sheetBoss3.crop(87, 110, 87, 110);
        boss3[6] = sheetBoss3.crop(87*2, 110, 87, 110);
        boss3[7] = sheetBoss3.crop(87*3, 110, 87, 110);

        levelup = new BufferedImage[8];
        levelup[0] = exclamation[0] .crop(0, 27, 24, 4);
        levelup[1] = exclamation[0] .crop(0, 23, 24, 8);
        levelup[2] = exclamation[0] .crop(0, 19, 24, 12);
        levelup[3] = exclamation[0] .crop(0, 15, 24, 16);
        levelup[4] = exclamation[0] .crop(0, 11, 24, 20);
        levelup[5] = exclamation[0] .crop(0, 7, 24, 24);
        levelup[6] = exclamation[0] .crop(0, 3, 24, 28);
        levelup[7] = exclamation[0] .crop(0, 0, 24, 32);

        spotted = new BufferedImage[8];
        spotted[0] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[1] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[2] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[3] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[4] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[5] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[6] = exclamation[1] .crop(0, 0, 8, 32);
        spotted[7] = exclamation[1] .crop(0, 0, 8, 32);

        unspotted = new BufferedImage[8];
        unspotted[0] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[1] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[2] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[3] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[4] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[5] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[6] = exclamation[2] .crop(0, 0, 24, 32);
        unspotted[7] = exclamation[2] .crop(0, 0, 24, 32);
    }
}