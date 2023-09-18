package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (var in = new BufferedReader(new FileReader(source));
             var outFile = new PrintWriter(new FileOutputStream(target))) {
            boolean status = true;
            while (in.ready()) {
                String[] part = in.readLine().split(" ");
                if (status && ("400".equals(part[0]) || "500".equals(part[0]))) {
                    status = false;
                    outFile.print(part[1] + ";");
                } else if (!status && ("200".equals(part[0]) || "300".equals(part[0]))) {
                    status = true;
                    outFile.println(part[1]);
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
