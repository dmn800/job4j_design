package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        validateGet(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String param : args) {
            String[] array = param.replace("-", "").split("=", 2);
            values.put(array[0], array[1]);
        }
    }

    public static ArgsName of(String[] args) {
        validate(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        "Error: This argument '%s' does not start with a '-' character".formatted(arg));
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        "Error: This argument '%s' does not contain an equal sign".formatted(arg));
            }
            String[] split = arg.replace("-", "").split("=", 2);
            if (split[0].equals("")) {
                throw new IllegalArgumentException(
                        "Error: This argument '%s' does not contain a key".formatted(arg));
            }
            if (split[1].equals("")) {
                throw new IllegalArgumentException(
                        "Error: This argument '%s' does not contain a value".formatted(arg));
            }
        }
    }

    private void validateGet(String arg) {
        if (!values.containsKey(arg)) {
            throw new IllegalArgumentException("This key: '%s' is missing".formatted(arg));
        }
    }
}
