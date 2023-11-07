package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();

        boolean goChat = true;
        while (goChat) {
            String answerUser = scanner.nextLine();
            log.add(answerUser);
            if (OUT.equals(answerUser)) {
                goChat = false;
                break;
            } else if (STOP.equals(answerUser)) {
                while (!CONTINUE.equals(answerUser)) {
                    answerUser = scanner.nextLine();
                    log.add(answerUser);
                }
            } else {
                String answerBot = readPhrases().get(new Random().nextInt(readPhrases().size() - 1));
                log.add(answerBot);
                System.out.println(answerBot);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> strings =  new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(strings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "data/log_console_chat.txt",
                "data/log_console_answers.txt"
        );
        cc.run();
    }

}
