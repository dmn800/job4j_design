package ru.job4j.ood.lsp.storefood.store;

import ru.job4j.ood.lsp.storefood.food.Food;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore {
    protected List<Food> foods = new ArrayList<>();
    protected Clock clock;

    protected AbstractStore(Clock clock) {
        this.clock = clock;
    }

    public boolean process(Food food) {
        if (accept(food)) {
            prepare(food);
            add(food);
            return true;
        }
        return false;
    }

    protected void add(Food food) {
        foods.add(food);
    }

    public List<Food> getAll() {
        return foods;
    }

    protected void prepare(Food food) {}

    protected abstract boolean accept(Food food);
}
