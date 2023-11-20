package ru.job4j.serialization.xml;

public class TypeEngine {
    private final String type;

    public TypeEngine(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeEngine{"
                + "type='" + type + '\''
                + '}';
    }
}
