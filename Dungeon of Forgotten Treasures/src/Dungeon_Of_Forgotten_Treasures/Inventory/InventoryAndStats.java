package Dungeon_Of_Forgotten_Treasures.Inventory;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;
import Dungeon_Of_Forgotten_Treasures.Graphics.Utility.StringDrawer;
import Dungeon_Of_Forgotten_Treasures.Handler;
import Dungeon_Of_Forgotten_Treasures.States.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InventoryAndStats {

    private Handler handler;
    private boolean active = false;
    private static InventoryAndStats self;
    private ArrayList<Item> items; //List of items
    private int x, y;
    private int selected;
    private int item_space;
    private boolean inventory_stats; //if true -> arata inventory. if false -> arata stat page

    private InventoryAndStats(Handler handler){
        this.handler = handler;
        x = handler.getGame().getWidth()/5;
        y = handler.getGame().getHeight()/5;
        items = new ArrayList<Item>();
        selected = 0;
        item_space = 40;
        inventory_stats = true;
    }

    public static InventoryAndStats getSelf(Handler handler)
    {
        if (self == null)
            self = new InventoryAndStats(handler);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void addItem(Item item)
    {
        for(Item it : items)
        {
            if(it.getId() == item.getId()) //daca e deja in inventar
            {
                it.setAmount(it.getAmount() + item.getAmount()); //cresti amount
                return;
            }
        }
        items.add(item);
    }

    public void Update()
    {
        if(State.getCurrentState() == handler.getGame().getGameState())
            if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_Q))
                active = !active; //if inventory is closed, open it. if it's open, close it.

        if(active) //if it's open
        {
            if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_W))
                selected--;
            if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_S))
                selected++;
            if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_D))
                inventory_stats = false;
            if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_A))
                inventory_stats = true;
            if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_E)&&!items.isEmpty()) {
                useItem(items.get(selected));
                handler.getWorld().playSE(3);
            }



            if (selected<0)
                selected = items.size() - 1;
            else if (selected >= items.size())
                selected = 0;
        }


    }

    public void Draw(Graphics graphics)
    {
        if(active)
        {
            graphics.drawImage(Assets.background, x, y, 640, 480, null);
            if (inventory_stats) {
                StringDrawer.StringDraw(graphics, "Items:", x + item_space, y + item_space, Color.white, Assets.font36);
                if (items.size() > 0) {
                    for (int i = 0; i < items.size(); i++) {
                        if (selected == i) {
                            StringDrawer.StringDraw(graphics, items.get(i).getAmount() + "  >" + items.get(i).getName() + "<", x + item_space, y + item_space*2 + i * item_space, Color.red, Assets.font36);
                            graphics.drawImage(items.get(i).texture, x + item_space, y + item_space * 9, Item.ITEMWIDTH * 3, Item.ITEMHEIGHT * 3, null);
                            StringDrawer.StringDraw(graphics, items.get(i).getDescription(), x+ item_space*4, y + item_space * 11, Color.white, Assets.font36);
                        } else
                            StringDrawer.StringDraw(graphics, items.get(i).getAmount() + "   " + items.get(i).getName() + " ", x + item_space, y + item_space*2 + i * item_space, Color.white, Assets.font36);
                    }
                }
            }
            else
            {
                StringDrawer.StringDraw(graphics, "Aaron the novice adventurer", x + item_space, y + item_space, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "Health: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()) + "/" + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getMax_health()), x + item_space, y + item_space*2, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "Mana: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getMana()) + "/" + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getMax_mana()), x + item_space, y + item_space*3, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "Stats:", x + item_space, y + item_space*5, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "Level: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getLevel()), x + item_space, y + item_space*6, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "EXP: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getExp()) + "/" + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getExp_cap()), x + item_space, y + item_space*7, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "INT: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getIntelligence()), x + item_space, y + item_space*8, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "DEF: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getDefense()), x + item_space, y + item_space*9, Color.white, Assets.font36);
                StringDrawer.StringDraw(graphics, "Score: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getScore()), x + item_space, y + item_space*11 +item_space/2, Color.white, Assets.font36);
            }
        }
    }

    private void useItem(Item item)
    {
        switch(item.getId())
        {
            case 0: handler.getWorld().getEntityManager().getPlayer().takeDamage(-10);
                break;
            case 1: handler.getWorld().getEntityManager().getPlayer().restoreMana(10);
                break;
            case 2: handler.getWorld().getEntityManager().getPlayer().setMax_health(handler.getWorld().getEntityManager().getPlayer().getMax_health()+5);
                break;
            case 3: handler.getWorld().getEntityManager().getPlayer().setMax_mana(handler.getWorld().getEntityManager().getPlayer().getMax_mana()+5);
                break;

        }
        items.get(selected).setAmount(items.get(selected).getAmount() - 1);
        if(items.get(selected).getAmount()<=0)
            items.remove(selected);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
