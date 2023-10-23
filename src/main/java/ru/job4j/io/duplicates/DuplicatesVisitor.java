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
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> mapPath = new HashMap<>();

    public Map<FileProperty, List<Path>> getMapPath() {
        return mapPath.entrySet().stream().filter(k -> k.getValue().size() >= 2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        mapPath.computeIfPresent(fileProperty, (k, v) -> {
            v.add(file.toAbsolutePath());
            return v;
        });
        mapPath.computeIfAbsent(fileProperty, k -> {
            List<Path> list = new ArrayList<>();
            list.add(file.toAbsolutePath());
            return list;
        });
        return super.visitFile(file, attrs);
    }
}
