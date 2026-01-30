package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.strategy.ShootStrategy;

import java.util.List;

/**
 * Abstract base class for all types of aircraft in the game.
 * Includes enemy aircraft (MOB, ELITE, BOSS) and the hero aircraft.
 * Defines common properties such as health, firepower, shooting behavior, and movement.
 *
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * Maximum and current health points (HP) of the aircraft.
     * When HP drops to 0, the aircraft is marked as invalid and will be removed.
     */
    protected int maxHp;
    protected int hp;
    protected ShootStrategy strategy;

    /**
     * Number of bullets fired per shot (burst count).
     * For example, shootNum = 3 means three bullets are fired in a single shooting action.
     */
    protected int shootNum = 1;

    /**
     * Damage power of each bullet fired by this aircraft.
     * This value is passed to bullets when they are created.
     */
    protected int power = 10;

    /**
     * Shooting direction:
     * -1 for upward (typically used by hero aircraft)
     *  1 for downward (typically used by enemy aircraft)
     */
    protected int direction = 1;

    /**
     * Constructs an aircraft with given position, speed, and initial health.
     *
     * @param locationX Initial X coordinate
     * @param locationY Initial Y coordinate
     * @param speedX    Horizontal speed
     * @param speedY    Vertical speed
     * @param hp        Initial health points
     */
    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    //Gets the current health points of the aircraft.
    public int getHp() {
        return hp;
    }
    public int getPower(){return power;}
    public int getDirection(){return direction;}

    /**
     * Abstract method for shooting bullets.
     * Must be implemented by subclasses to define shooting behavior.
     *
     * @return a list of BaseBullet objects representing bullets fired, or empty list if not applicable
     */
    public List<BaseBullet> shoot() {
        return strategy.execute(this);
    }
    /**
     * Increases the HP of the aircraft.
     * The HP will not exceed the maximum HP.
     *
     * @param increasedHp amount of health to add
     */
    public void increaseHp(int increasedHp) {
        // TODO: Implement logic to increase HP, ensuring it does not exceed maxHp
        if (this.getHp() < this.maxHp){
            if (this.getHp() + increasedHp > this.maxHp){
                hp = this.maxHp;
            }else{
                hp += increasedHp;
            }
        }
    }

    /**
     * Decreases the HP of the aircraft when hit by bullets or collisions.
     * If HP drops to zero or below, the aircraft is marked as invalid and will be removed.
     */
    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }
    public void setStrategy(ShootStrategy strategyName){
        this.strategy = strategyName;
    }
}


