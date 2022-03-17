package Dungeon_Of_Forgotten_Treasures.Inventory;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public static final int ITEMWIDTH = 32;
    public static final int ITEMHEIGHT = 32;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected String description;
    protected int amount;
    protected final int id;

    public static Item[] items = new Item[50];
    public static Item health_potion = new Item(Assets.health_potion, "Ancient Potion of Healing", "heals for 10 HP", 0);
    public static Item mana_potion = new Item(Assets.mana_potion, "Ancient Potion of Mana", "restores 10 MP", 1);
    public static Item max_health_potion = new Item(Assets.max_health_potion, "Corrupt Potion of Vigor", "increases total HP by 5", 2);
    public static Item max_mana_potion = new Item(Assets.max_mana_potion, "Corrupt Potion of Spirit","increases total MP by 5", 3);

    public Item(BufferedImage texture, String name, String description, int id)
    {
        this.texture = texture;
        this.name = name;
        this.id = id;
        amount = 1;
        this.description = description;
    }

    public Item createNew() //make a copy. (what gets added to inventory)
    {
        return new Item(texture, name, description, id);
    }

    public void Update()
    {
        if(handler == null)
            return;
        handler.getWorld().getEntityManager().getPlayer().getPlayerInventoryAndStats().addItem(this);
    }

    public void Draw(Graphics graphics, int x, int y)
    {
        graphics.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
