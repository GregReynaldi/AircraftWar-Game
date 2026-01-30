package edu.hitsz.factory.supply_factory;

import edu.hitsz.supply.AbstractFlyingSupply;
import edu.hitsz.supply.FirePowerSupply;

public class FireSupplyFactory implements SupplyFactory{
    public AbstractFlyingSupply createProduct(int locX, int locY){
        return new FirePowerSupply(locX, locY,0,3);
    };
}
