package ru.job4j.serialization.json;

public class TypeEngine {
    private final String type;

    public TypeEngine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TypeEngine{"
                + "type='" + type + '\''
                + '}';
    }
}
