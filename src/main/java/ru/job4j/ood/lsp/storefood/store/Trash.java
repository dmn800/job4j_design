package ru.job4j.ood.lsp.storefood.store;

import ru.job4j.ood.lsp.storefood.food.Food;

import java.time.Clock;

public class Trash extends AbstractStore {
    public Trash(Clock clock) {
        super(clock);
    }

    @Override
    protected boolean accept(Food food) {
        return food.isExpired(clock);
    }
}
