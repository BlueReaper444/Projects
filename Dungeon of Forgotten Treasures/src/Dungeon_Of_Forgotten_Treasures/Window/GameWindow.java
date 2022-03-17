package Dungeon_Of_Forgotten_Treasures.Window;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame;
    private Canvas canvas;

    private final String title;
    private final int width;
    private final int height;

    private static GameWindow self;

    private GameWindow(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        createWindow(); //to keep constructor clean
    }

    public static GameWindow getSelf(String title, int width, int height)
    {
        if (self == null)
            self = new GameWindow(title, width, height);
        return self;
    }

    public static void reset()
    {
        self = null;
    }


    private void createWindow()
    {
        if (frame != null)
            return;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so it won't sit in the BG

        frame.setResizable(false); //can't drag the window
        frame.setLocationRelativeTo(null); //window appears in the center of screen
        frame.setVisible(true); //frames aren't visible by default

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        //makes sure you keep the canvas within those dimensions

        canvas.setFocusable(false);
        //super important. so only the JFrame has focus.

        frame.add(canvas); //add the canvas to frame
        frame.pack(); //resize window to see canvas fully
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
