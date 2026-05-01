package ru.job4j.ood.lsp.storefood.quality;

import ru.job4j.ood.lsp.storefood.food.Food;
import ru.job4j.ood.lsp.storefood.store.AbstractStore;

import java.util.List;

public class ControlQuality {
    private List<AbstractStore> stores;

    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        for (AbstractStore store : stores) {
            if (store.process(food)) {
                break;
            }
        }
    }
}
