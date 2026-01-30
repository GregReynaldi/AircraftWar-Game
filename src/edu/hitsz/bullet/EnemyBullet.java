package edu.hitsz.bullet;

/**
 * Represents a bullet fired by an enemy aircraft.
 * This class inherits from BaseBullet and uses its movement and collision logic.
 * The bullet moves in a straight line based on its speed and is removed when it goes out of bounds.
 */
public class EnemyBullet extends BaseBullet {

    //Constructs an enemy bullet with the specified position, velocity, and damage power.
    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

}
