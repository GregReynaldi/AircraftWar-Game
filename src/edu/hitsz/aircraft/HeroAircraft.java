package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;

public class HeroAircraft extends AbstractAircraft {
    private static HeroAircraft instance = null;

    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.power = 30;    // Stronger firepower than enemies
        this.direction = -1;  // Shoots upward
        this.setStrategy(new StraightShoot());
    }
    public static HeroAircraft getInstance(int locationX, int locationY, int speedX, int speedY, int hp){
        if (instance == null){
            instance = new HeroAircraft(locationX, locationY, speedX, speedY, hp);
        }
        return instance;
    }
    public static HeroAircraft getInstance(){
        return instance;
    }
    @Override
    public void forward() {
    }
}

