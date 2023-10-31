package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough args");
        }
        if (!new File(args[0]).exists()) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!args[1].endsWith(".js")) {
            throw new IllegalArgumentException("Incorrect format");
        }
    }

}
