package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller class for the hero aircraft.
 * Listens to mouse input and controls the movement of the hero aircraft in real time.
 * Ensures the aircraft stays within the game window boundaries.
 */
public class HeroController {
    private Game game;                    // Reference to the main game panel
    private HeroAircraft heroAircraft;    // The hero aircraft object to be controlled
    private MouseAdapter mouseAdapter;    // Mouse listener for movement (drag and motion)

    /**
     * Constructs a HeroController to link mouse input with hero aircraft movement.
     *
     * @param game          the main game panel that receives mouse events
     * @param heroAircraft  the hero aircraft instance to be controlled
     */
    public HeroController(Game game, HeroAircraft heroAircraft){
        this.game = game;
        this.heroAircraft = heroAircraft;

        // Create a mouse adapter to handle dragging events (also captures motion)
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX();    // Get current mouse X position
                int y = e.getY();    // Get current mouse Y position

                // Prevent the aircraft from moving outside the window boundaries
                if ( x<0 || x>Main.WINDOW_WIDTH || y<0 || y>Main.WINDOW_HEIGHT){
                    // Ignore input if mouse is outside the game window
                    return;
                }
                // Update the hero aircraft's position to follow the mouse
                heroAircraft.setLocation(x, y);
            }
        };

        // Register the mouse listener to the game panel
        game.addMouseListener(mouseAdapter);
        game.addMouseMotionListener(mouseAdapter);
    }

}
