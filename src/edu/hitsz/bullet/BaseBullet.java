package edu.hitsz.bullet;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * Abstract base class representing a bullet in the game.
 * This class defines common properties of all bullets, such as damage power.
 * Bullets move according to their speed and are removed when they go out of bounds.
 */
public abstract class BaseBullet extends AbstractFlyingObject {

    //The damage power of this bullet. Determines how much damage it inflicts on enemies or the hero.
    private int power = 10;

    //Constructs a bullet with specified position, velocity, and damage powe
    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    /**
     * Updates the bullet's position based on its speed.
     * Overrides the forward() method from Abs  tractFlyingObject to add boundary checks.
     * The bullet is marked as invalid (vanished) if it moves off-screen.
     */
    @Override
    public void forward() {
        super.forward();

        // Check if the bullet has gone out of bounds horizontally
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }
        // Check if the bullet has gone out of bounds vertically
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // Bullet moving downward has exited the bottom of the screen
            vanish();
        }else if (locationY <= 0){
            // Bullet moving upward has exited the top of the screen
            vanish();
        }
    }

    /**
     * Gets the damage power of this bullet.
     *
     * @return the damage value (power) of the bullet
     */
    public int getPower() {
        return power;
    }
}
