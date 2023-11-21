package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isElectric() {
        return electric;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    public TypeEngine getType() {
        return type;
    }

    public String[] getFeatures() {
        return features;
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
        JSONObject jsonTypeEngine = new JSONObject("{\"engine\":\"Fuel\"}");

        List<String> list = new ArrayList<>();
        list.add("1.8 L");
        list.add("500 km");
        JSONArray jsonFeatures = new JSONArray(list);

        final Engine engine = new Engine(true, 1000000,
                "Tesla", new TypeEngine("Electric"), new String[] {"25 MW", "400 km"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("electric", engine.isElectric());
        jsonObject.put("price", engine.getPrice());
        jsonObject.put("company", engine.getCompany());
        jsonObject.put("type", jsonTypeEngine);
        jsonObject.put("features", jsonFeatures);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(engine));
    }
}
