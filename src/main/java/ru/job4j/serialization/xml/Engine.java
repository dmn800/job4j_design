package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.TypeEngine;

import java.util.Arrays;

public class Engine {
    private final boolean electric;
    private final double price;
    private final String company;
    private final TypeEngine type;
    private final String[] features;

    public Engine(boolean electric, double price, String company, TypeEngine type, String[] features) {
        this.electric = electric;
        this.price = price;
        this.company = company;
        this.type = type;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "electric=" + electric
                + ", price=" + price
                + ", company='" + company + '\''
                + ", type=" + type
                + ", features=" + Arrays.toString(features)
                + '}';
    }

    public static void main(String[] args) {
        final Engine engine = new Engine(true,
                1000000, "Tesla",
                new TypeEngine("Electric"),
                new String[] {"25 MW", "400 km"}
        );
    }
}
