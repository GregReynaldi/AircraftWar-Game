package edu.hitsz.supply;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.basic.AbstractFlyingObject;


/**
 * Abstract base class for all supply items in the game.
 * This includes supplies such as firepower upgrades, and health recovery items.
 * These items move downward on the screen and take effect when collected by the hero aircraft.
 */

public abstract class AbstractFlyingSupply extends AbstractFlyingObject {

    public AbstractFlyingSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * Activates the effect of this supply when it collides with the hero aircraft.
     * The specific behavior (e.g., change the bullet trajectory, increase the hero's health points.)
     * is defined in subclasses.
     *
     * @param abstractAircraft the hero aircraft that collected this supply
     */
    public abstract void activate(AbstractAircraft abstractAircraft);
}
