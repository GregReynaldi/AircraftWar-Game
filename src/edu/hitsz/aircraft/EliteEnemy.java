package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.supply_factory.FireSupplyFactory;
import edu.hitsz.factory.supply_factory.HpSupplyFactory;
import edu.hitsz.factory.supply_factory.SuperFireFactory;
import edu.hitsz.factory.supply_factory.SupplyFactory;
import edu.hitsz.strategy.StraightShoot;
import edu.hitsz.supply.AbstractFlyingSupply;
import edu.hitsz.supply.FirePowerSupply;
import edu.hitsz.supply.HealthSupply;
import edu.hitsz.supply.SuperFireSupply;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EliteEnemy extends AbstractEnemyAircraft {
    private SupplyFactory factory_supply;
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.setStrategy(new StraightShoot());
    }
    @Override
    public int score() {
        return 10;
    }

    @Override
    public List<AbstractFlyingSupply> generateSupplies() {
        LinkedList <AbstractFlyingSupply> supplyDropped = new LinkedList<AbstractFlyingSupply>();
        Random randomProbabilityDroppedSomething = new Random();
        double p = randomProbabilityDroppedSomething.nextDouble();
        if (p>=0.4) {
            double p_2 = randomProbabilityDroppedSomething.nextDouble();
            if (p_2 > 0.5) {
                double p_3 = randomProbabilityDroppedSomething.nextDouble();
                if (p_3 > 0.5){
                    factory_supply = new HpSupplyFactory();
                }else{
                    factory_supply = new FireSupplyFactory();
                }
            }else{
                factory_supply = new SuperFireFactory();
            }
            supplyDropped.add(factory_supply.createProduct(this.getLocationX(),this.getLocationY()));
        }
        return supplyDropped;
    }
}
