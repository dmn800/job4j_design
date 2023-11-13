package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        try (Scanner scannerLine = new Scanner(new BufferedReader(new FileReader(argsName.get("path"))))) {

            Scanner scannerHead = new Scanner(new StringReader(scannerLine.nextLine())).useDelimiter(argsName.get("delimiter"));
            List<Integer> numberHead = new ArrayList<>();
            List<String> listFilter = new ArrayList<>(Arrays.asList(argsName.get("filter").split(",")));

            while (scannerHead.hasNext()) {
                String head = scannerHead.next();
                numberHead.add(listFilter.contains(head) ? listFilter.indexOf(head) : null);
            }

            StringJoiner sjHead = new StringJoiner(argsName.get("delimiter"));
            for (String lf : listFilter) {
                sjHead.add(lf);
            }
            StringJoiner sjLine = new StringJoiner(System.lineSeparator()).add(sjHead.toString());

            File file = new File(argsName.get("out"));

            while (scannerLine.hasNext()) {
                StringJoiner sjColumn = new StringJoiner(argsName.get("delimiter"));
                Scanner scannerColumn = new Scanner(new StringReader(scannerLine.nextLine())).useDelimiter(argsName.get("delimiter"));
                int countColumn = 0;
                List<String> line = new ArrayList<>(listFilter);
                while (scannerColumn.hasNext()) {
                    Integer index = numberHead.get(countColumn);
                    if (index != null) {
                        line.set(index, scannerColumn.next());
                    } else {
                        scannerColumn.next();
                    }
                    countColumn++;
                }
                for (String ln : line) {
                    sjColumn.add(ln);
                }
                sjLine.add(sjColumn.toString());
            }

            if (argsName.get("out").equals("stdout")) {
                System.out.println(sjLine);
            } else {
                try (PrintStream pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    pw.println(sjLine);
                }
            }
        }
    }

    private static void validate(ArgsName args) throws Exception {
        if (!Files.exists(Path.of(args.get("path")))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!(args.get("delimiter").equals(";") || args.get("delimiter").equals(","))) {
            throw new IllegalArgumentException("Incorrect format separate");
        }
        if (!(args.get("out").equals("stdout") || args.get("out").endsWith(".csv"))) {
            throw new IllegalArgumentException("Incorrect output format");
        }

        String[] filter1 = args.get("filter").split(",");
        List<String> heads = new ArrayList<>();

        try (Scanner scannerLine = new Scanner(new BufferedReader(new FileReader(args.get("path"))))) {
            Scanner scannerHead = new Scanner(new StringReader(scannerLine.nextLine())).useDelimiter(args.get("delimiter"));
            while (scannerHead.hasNext()) {
                heads.add(scannerHead.next());
            }
        }

        for (String head : filter1) {
            if (!(heads.contains(head))) {
                throw new IllegalArgumentException("Incorrect filter");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }

}
