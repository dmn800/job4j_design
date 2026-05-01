package ru.job4j.ood.lsp.storefood.quality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storefood.food.Food;
import ru.job4j.ood.lsp.storefood.food.Milk;
import ru.job4j.ood.lsp.storefood.store.Shop;
import ru.job4j.ood.lsp.storefood.store.Trash;
import ru.job4j.ood.lsp.storefood.store.Warehouse;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ControlQualityTest {

    private Clock fixedClock() {
        return Clock.fixed(
                LocalDate.of(2026, 1, 10)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()
        );
    }

    @Test
    void whenFreshThenWarehouse() {
        Clock clock = fixedClock();
        Warehouse warehouse = new Warehouse(clock);
        Shop shop = new Shop(clock);
        Trash trash = new Trash(clock);
        ControlQuality control = new ControlQuality(
                List.of(warehouse, shop, trash)
        );
        Food food = new Milk(
                "Milk",
                LocalDate.of(2026, 1, 9),
                LocalDate.of(2026, 1, 20),
                100
        );
        control.distribute(food);
        assertThat(warehouse.getAll()).contains(food);
    }

    @Test
    void whenMiddleThenShop() {
        Clock clock = fixedClock();
        Warehouse warehouse = new Warehouse(clock);
        Shop shop = new Shop(clock);
        Trash trash = new Trash(clock);
        ControlQuality control = new ControlQuality(
                List.of(warehouse, shop, trash)
        );
        Food food = new Milk(
                "Milk",
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 20),
                100
        );
        control.distribute(food);
        assertThat(shop.getAll()).contains(food);
    }

    @Test
    void whenExpiredThenTrash() {
        Clock clock = fixedClock();
        Warehouse warehouse = new Warehouse(clock);
        Shop shop = new Shop(clock);
        Trash trash = new Trash(clock);
        ControlQuality control = new ControlQuality(
                List.of(warehouse, shop, trash)
        );
        Food food = new Milk(
                "Milk",
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2026, 1, 5),
                100
        );
        control.distribute(food);
        assertThat(trash.getAll()).contains(food);
    }

    @Test
    void whenOldThenDiscountAppliedAndGoesToShop() {
        Clock clock = fixedClock();
        Warehouse warehouse = new Warehouse(clock);
        Shop shop = new Shop(clock);
        Trash trash = new Trash(clock);
        ControlQuality control = new ControlQuality(
                List.of(warehouse, shop, trash)
        );
        Food food = new Milk(
                "Milk",
                LocalDate.of(2026, 1, 1),   // прошло 9 дней
                LocalDate.of(2026, 1, 11),  // всего 10 дней ? 90%
                100
        );
        control.distribute(food);
        assertThat(shop.getAll()).contains(food);
        assertThat(food.getPrice()).isEqualTo(80);
    }

    @Test
    void whenProcessTwiceThenDiscountAppliedOnce() {
        Clock clock = fixedClock();
        Shop shop = new Shop(clock);
        Food food = new Milk(
                "Milk",
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 11),
                100
        );
        shop.process(food);
        shop.process(food);
        assertThat(food.getPrice()).isEqualTo(80);
    }
}