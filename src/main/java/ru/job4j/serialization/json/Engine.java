package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        final Engine engine = new Engine(true, 1000000,
                "Tesla", new TypeEngine("Electric"), new String[] {"25 MW", "400 km"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(engine));

        final String engineJson =
                "{"
                        + "\"electric\":false,"
                        + "\"price\":500000.0,"
                        + "\"company\":\"Rolls-Royce\","
                        + "\"type\":"
                        + "{"
                        + "\"type\":\"Fuel\""
                        + "},"
                        + "\"features\":"
                        + "[\"1.8 L\", \"500 km\"]"
                        + "}";
        final Engine engineMod = gson.fromJson(engineJson, Engine.class);
        System.out.println(engineMod);
    }
}
