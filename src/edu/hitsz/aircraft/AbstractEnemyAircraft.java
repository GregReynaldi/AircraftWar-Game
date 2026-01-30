package edu.hitsz.aircraft;

import edu.hitsz.supply.AbstractFlyingSupply;

import java.util.List;

/**
 * Abstract base class for all types of enemy aircraft.
 * Extends AbstractAircraft and adds enemy-specific behaviors such as scoring and drop rewards.
 */
public abstract class AbstractEnemyAircraft extends AbstractAircraft {
    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    /**
     * Gets the score awarded when this enemy aircraft is destroyed.
     * The scoring logic is defined by subclasses based on enemy type (e.g., MOB, ELITE, BOSS).
     *
     * @return the points granted to the player upon destroying this enemy
     */
    public abstract int score();


    /**
     * Generates power-up supplies (e.g.,  firepower upgrades, health recovery items) when the enemy is destroyed.
     * The drop is typically based on a probability and defined by the subclass.
     *
     * @return a list of AbstractFlyingSupply objects to be dropped, or an empty list if no item is dropped
     */
    public abstract List<AbstractFlyingSupply> generateSupplies();


}
