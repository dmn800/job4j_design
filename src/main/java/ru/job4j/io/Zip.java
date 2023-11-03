package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path dir : sources) {
                zip.putNextEntry(new ZipEntry(dir.toAbsolutePath().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(dir.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName jvm = ArgsName.of(args);
        validate(jvm, args);
        zip.packFiles(
                search(Paths.get(jvm.get("d")), p -> !p.toFile().getName().endsWith(jvm.get("e"))),
                new File(jvm.get("o")));
    }

    private static void validate(ArgsName jvm, String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough args");
        }
        if (!Files.exists(Paths.get(jvm.get("d")))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!jvm.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Incorrect format Exclude");
        }
        if (!jvm.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Incorrect format ZIP");
        }
    }
}
