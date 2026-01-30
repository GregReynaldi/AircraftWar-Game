package edu.hitsz.supply;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.strategy.SpreadShoot;
import edu.hitsz.strategy.StraightShoot;

public class FirePowerSupply extends AbstractFlyingSupply {
    public FirePowerSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void activate(AbstractAircraft abstractAircraft){
        abstractAircraft.setStrategy(new SpreadShoot());
        
        Runnable timerRunnable = () -> {
            try {
                Thread.sleep(5000);
                abstractAircraft.setStrategy(new StraightShoot());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        Thread timerThread = new Thread(timerRunnable);
        timerThread.start();
    }
}