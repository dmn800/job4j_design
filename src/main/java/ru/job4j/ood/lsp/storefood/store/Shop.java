package ru.job4j.ood.lsp.storefood.store;

import ru.job4j.ood.lsp.storefood.food.Food;

import java.time.Clock;

public class Shop extends AbstractStore {
    public Shop(Clock clock) {
        super(clock);
    }

    @Override
    protected boolean accept(Food food) {
        double percent = food.getShelfLifePercent(clock);
        return percent >= 0.25 && percent < 1.0;
    }

    @Override
    protected void prepare(Food food) {
        double percent = food.getShelfLifePercent(clock);
        if (percent > 0.75) {
            food.applyDiscount(0.2);
        }
    }
}
