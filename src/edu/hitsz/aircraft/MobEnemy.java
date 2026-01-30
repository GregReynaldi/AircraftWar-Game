package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.supply.AbstractFlyingSupply;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a basic enemy aircraft (Mob enemy).
 * This type of enemy moves downward and cannot shoot.
 * It is typically the simplest and most common enemy in the game.
 */
public class MobEnemy extends AbstractEnemyAircraft {

    /**
     * Constructs a Mob enemy with the specified position, speed, and health.
     *
     * @param locationX Initial X coordinate
     * @param locationY Initial Y coordinate
     * @param speedX    Horizontal speed
     * @param speedY    Vertical speed
     * @param hp        Initial health points
     */
    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    /**
     * Returns the score awarded when this Mob enemy is destroyed.
     *
     * @return 10 points
     */
    @Override
    public int score() {
        return 10;
    }

    /**
     * Determines the power-ups dropped when this enemy is destroyed.
     * Mob enemies currently do not drop any supplies.
     *
     * @return an empty list, indicating no items are dropped
     */
    @Override
    public List<AbstractFlyingSupply> generateSupplies() {
        return new LinkedList<>();
    }


    /**
     * Shoots bullets. Mob enemies cannot shoot.
     *
     * @return an empty list, indicating this enemy does not fire any bullets
     */
    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

}
