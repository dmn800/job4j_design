package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> mapPath = new HashMap<>();

    public void getMapPath() {
        mapPath.entrySet()
                .stream()
                .filter(k -> k.getValue().size() > 1)
                .forEach(System.out::println);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (mapPath.containsKey(fileProperty)) {
            mapPath.get(fileProperty).add(file.toAbsolutePath());
        } else {
            List<Path> list = new ArrayList<>();
            list.add(file.toAbsolutePath());
            mapPath.put(fileProperty, list);
        }
        return super.visitFile(file, attrs);
    }
}
