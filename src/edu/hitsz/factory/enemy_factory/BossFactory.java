package edu.hitsz.factory.enemy_factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import java.lang.Math.*;
import java.util.Random;

public class BossFactory implements EnemyFactory{
    public BossFactory(){};

    @Override
    public AbstractEnemyAircraft createEnemy() {
        Random direction = new Random();
        if (direction.nextDouble() > 0.5){
            return new BossEnemy(
                    (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
                    (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), 3, 0, 150
            );
        }
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), -3, 0, 150
        );
    }
}
