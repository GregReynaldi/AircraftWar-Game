package edu.hitsz.factory.enemy_factory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobFactory implements EnemyFactory{
    public MobFactory(){};

    @Override
    public AbstractEnemyAircraft createEnemy(){
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), 0, 10, 10
        );
    }
}
