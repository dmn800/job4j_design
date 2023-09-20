package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (var in = new BufferedReader(new FileReader(source));
             var outFile = new PrintWriter(new FileOutputStream(target))) {
            boolean switcher = false;
            while (in.ready()) {
                String[] part = in.readLine().split(" ");
                boolean status = "400".equals(part[0]) || "500".equals(part[0]);
                if (status ^ switcher) {
                    outFile.print(status ? "%s;".formatted(part[1]) : "%s%n".formatted(part[1]));
                    switcher = status;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
