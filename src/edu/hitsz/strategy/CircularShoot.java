package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class CircularShoot implements ShootStrategy{
    public CircularShoot(){};
    public List<BaseBullet> execute(AbstractAircraft plane){
        List<BaseBullet> res = new LinkedList<>();
        int shootNum_par = 20;
        //the x-coordinate and y-coordinate of boss
        int x = plane.getLocationX();
        int y = plane.getLocationY();
        int bulletSpeed = 10;
        int bulletRadius = 100;
        int angleIncrement = 360 / shootNum_par;
        int power = plane.getPower();
        BaseBullet bullet;
        for (int i = 0; i < shootNum_par; i++) {
            int angle = i * angleIncrement;
            int bulletX = (int) (x + bulletRadius * Math.cos(Math.toRadians(angle)));
            int bulletY = (int) (y + bulletRadius * Math.sin(Math.toRadians(angle)));
            int bulletSpeedX = (int) (bulletSpeed * Math.cos(Math.toRadians(angle)));
            int bulletSpeedY = (int) (bulletSpeed * Math.sin(Math.toRadians(angle)));
            if (plane instanceof HeroAircraft){
                bullet = new HeroBullet(bulletX , bulletY, bulletSpeedX, bulletSpeedY, power);
            }else{
                bullet = new EnemyBullet(bulletX , bulletY, bulletSpeedX, bulletSpeedY, power);
            }
            res.add(bullet);
        }
        return res;
    }
}
