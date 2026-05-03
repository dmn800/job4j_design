package ru.job4j.ood.lsp.parking.vehicle;

public class Truck implements Vehicle {

    private final int size;

    public Truck(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Truck size must be > 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
