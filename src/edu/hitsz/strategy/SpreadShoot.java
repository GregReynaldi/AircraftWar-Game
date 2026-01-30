package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class SpreadShoot implements ShootStrategy{
    public SpreadShoot(){};
    public List<BaseBullet> execute(AbstractAircraft plane){
        List<BaseBullet> res = new LinkedList<>();
        int x = plane.getLocationX();
        int y = plane.getLocationY() + plane.getDirection()*2;
        int speedX = 0;
        int speedY = plane.getSpeedY() + plane.getDirection()*5;
        int power = plane.getPower();
        BaseBullet bullet;
        int shootNum_par = 5;
        for(int i=0; i<shootNum_par; i++){
            if(plane instanceof HeroAircraft){
                bullet = new HeroBullet(x + (i * 2 - shootNum_par + 1) * 10, y, speedX + (i * 2 - shootNum_par + 1), speedY, power);
            }else{
                bullet = new EnemyBullet(x + (i * 2 - shootNum_par + 1) * 10, y, speedX + (i * 2 - shootNum_par + 1), speedY, power);
            }
            res.add(bullet);
        }
        return res;
    }
}
