package Dungeon_Of_Forgotten_Treasures;

import Dungeon_Of_Forgotten_Treasures.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Light {
    private BufferedImage lightSouce;
    private int x;
    private int y;
    private int radius;
    private int luminosity;

    public Light(int x, int y, int radius, int luminosity)
    {
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.luminosity=luminosity;
        lightSouce = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) lightSouce.getGraphics();

        int step=4;
        int numSteps=radius/step;
        //g2.setColor(new Color(251,183,65,this.luminosity));
        g2.setColor(new Color(0, 0, 0, this.luminosity));
        for(int i=0;i<numSteps;++i)
        {
            g2.fillOval(radius-i*step,radius-i*step,i*step*2,i*step*2);
        }
    }
    public void update(){

    }

    public void render(Graphics graphics)
    {
        /*int step=4;
        int numSteps=radius/step;
        //g2.setColor(new Color(0,0,0,this.luminosity));
        graphics.setColor(new Color(0,0,0,this.luminosity));
        for(int i=0;i<numSteps;++i)
        {
            graphics.fillOval(radius-i*step,radius-i*step,i*step*2,i*step*2);
        }*/
        graphics.drawImage(lightSouce, x-radius,  y-radius, radius*2, radius*2, null);
    }

}