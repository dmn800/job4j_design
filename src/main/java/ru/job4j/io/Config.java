package ru.job4j.io;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;


public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines().filter(s -> !s.startsWith("#") && !s.isEmpty())
                    .forEach(s -> {
                        String[] part = s.split("=", 2);
                        if (part.length != 2 || !s.contains("=") || part[0].isEmpty() || part[1].isEmpty()) {
                            throw new IllegalArgumentException("Invalid line: %s".formatted(s));
                        }
                        values.put(part[0], part[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("data/app.properties");
        config.load();
        Class.forName(config.value("class_name"));
        String url = config.value("string_url");
        String login = config.value("string_login");
        String password = config.value("string_password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
