package ru.job4j.ood.lsp.food;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double price;
    private boolean discountApplied;

    protected Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public double getShelfLifePercent(Clock clock) {
        long total = ChronoUnit.DAYS.between(createDate, expiryDate);
        if (total == 0) {
            return 1.0;
        }
        long used = ChronoUnit.DAYS.between(createDate, LocalDate.now(clock));
        return (double) used / total;
    }

    public boolean isExpired(Clock clock) {
        return LocalDate.now(clock).isAfter(expiryDate);
    }

    public void applyDiscount(double discount) {
        if (!discountApplied) {
            this.price = price * (1 - discount);
            this.discountApplied = true;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
