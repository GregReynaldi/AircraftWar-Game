package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;

/**
 * Main entry point of the Aircraft War game application.
 * Initializes the game window, sets up the game panel, and starts the game loop.
 */
public class Main {

    //Fixed width of the game window in pixels.
    public static final int WINDOW_WIDTH = 512;
    //Fixed height of the game window in pixels.
    public static final int WINDOW_HEIGHT = 768;

    /**
     * Program entry method. Initializes and displays the game window,
     * then starts the main game loop.
     */
    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // Get the screen size to center the game window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Create the main game window (JFrame)
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        // Position the window centered horizontally on screen
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the game panel and add it to the window
        Game game = new Game();
        frame.add(game);
        frame.setVisible(true);

        // Start the main game loop
        game.action();
    }
}
