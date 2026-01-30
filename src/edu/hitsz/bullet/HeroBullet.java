package edu.hitsz.bullet;

/**
 * Represents a bullet fired by the hero (player's) aircraft.
 * This class inherits from BaseBullet and uses its movement and damage mechanics.
 * Hero bullets move upward and are removed when they go out of screen bounds.
 */
public class HeroBullet extends BaseBullet {

    //Constructs a bullet fired by the hero aircraft with the specified position, velocity, and damage power.
    public HeroBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

}
