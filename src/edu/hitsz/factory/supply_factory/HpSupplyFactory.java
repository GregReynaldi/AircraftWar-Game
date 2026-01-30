package edu.hitsz.factory.supply_factory;

import edu.hitsz.supply.AbstractFlyingSupply;
import edu.hitsz.supply.HealthSupply;

public class HpSupplyFactory implements  SupplyFactory{
    public AbstractFlyingSupply createProduct(int locX, int locY){
        return new HealthSupply(locX,locY,0,3);
    };
}
