package edu.hitsz.factory.supply_factory;

import edu.hitsz.supply.AbstractFlyingSupply;
import edu.hitsz.supply.FirePowerSupply;
import edu.hitsz.supply.SuperFireSupply;

public class SuperFireFactory implements SupplyFactory{
    public AbstractFlyingSupply createProduct(int locX, int locY){
        return new SuperFireSupply(locX, locY,0,3);
    };
}