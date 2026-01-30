package edu.hitsz.supply;

import edu.hitsz.aircraft.AbstractAircraft;

public class HealthSupply extends AbstractFlyingSupply{
    public HealthSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void activate(AbstractAircraft abstractAircraft){
        abstractAircraft.increaseHp(20);
    };
}
