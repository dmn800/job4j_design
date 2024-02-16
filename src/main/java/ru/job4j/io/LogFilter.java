package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines().filter(s -> {
                String[] array = s.split(" ");
                String el = array[array.length - 2];
                return "404".equals(el);
            }).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter outFile = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                )
        )) {
            for (String str : data) {
                outFile.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log_zgc.txt");
        logFilter.filter().forEach(System.out::println);
        logFilter.saveTo("data/404.txt");
    }
}
