package ru.job4j.ood.lsp.storefood.food;

import java.time.LocalDate;

public class Milk extends Food {
    public Milk(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
