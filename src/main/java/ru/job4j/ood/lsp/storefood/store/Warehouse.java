package ru.job4j.ood.lsp.storefood.store;

import ru.job4j.ood.lsp.storefood.food.Food;

import java.time.Clock;

public class Warehouse extends AbstractStore {
    public Warehouse(Clock clock) {
        super(clock);
    }

    @Override
    protected boolean accept(Food food) {
        return food.getShelfLifePercent(clock) < 0.25;
    }
}
