package edu.hitsz.factory.enemy_factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.Random;

public class EliteFactory implements EnemyFactory{
    public EliteFactory(){};

    @Override
    public AbstractEnemyAircraft createEnemy() {
        Random direction = new Random();
        if (direction.nextDouble() > 0.5){
            return new EliteEnemy(
                    (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                    (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), 3, 10, 10
            );
        }
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), -3, 10, 10
        );
    }
}
