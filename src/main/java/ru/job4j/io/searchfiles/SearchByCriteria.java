package ru.job4j.io.searchfiles;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchByCriteria {
    public static void main(String[] args) throws IOException {
        String input;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль недоступна");
            return;
        }
        input = console.readLine("%s\n", "Введите параметры: ");
        String[] arg = input.split(" ");
        validate(arg);

        ArgsName jvm = ArgsName.of(arg);
        Path start = Paths.get(jvm.get("d"));
        String name = jvm.get("n");
        String type = jvm.get("t");
        StringBuilder sb = new StringBuilder("ru/job4j/io/searchfiles/");
        File file = new File(sb.append(jvm.get("o")).toString());

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
            if ("name".equals(type)) {
                search(start, p -> p.toFile().getName().equals(name)).forEach(pw::println);
            }
            if ("mask".equals(type)) {
                String nameRegex = name.replace("*", "\\S*")
                        .replace(".", "[.]")
                        .replace("?", "\\S?");
                search(start, p -> p.toFile().getName().matches(nameRegex)).forEach(pw::println);
            }
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] args) {
        ArgsName jvm = ArgsName.of(args);
        if (args.length < 4) {
            throw new IllegalArgumentException("Not enough args");
        }
        if (!new File(jvm.get("d")).isDirectory()) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!("name".equals(jvm.get("t")) || "mask".equals(jvm.get("t")))) {
            throw new IllegalArgumentException("Incorrect search type");
        }
        if (!jvm.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("Incorrect output file");
        }
    }
}
