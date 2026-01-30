package edu.hitsz.factory.supply_factory;

import edu.hitsz.supply.AbstractFlyingSupply;

public interface SupplyFactory {
    public AbstractFlyingSupply createProduct(int locX, int locY);
}
