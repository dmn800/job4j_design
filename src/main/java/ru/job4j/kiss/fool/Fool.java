package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        int startAt = 1;
        Scanner input = new Scanner(System.in);
        while (startAt < 100) {
            String correct = fizzBuzz(startAt);
            System.out.println(correct);
            startAt++;
            String answer = input.nextLine();
            if (!isCorrectAnswer(startAt, answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 1;
                continue;
            }
            startAt++;
        }
    }

    static String fizzBuzz(int number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }

    static boolean isCorrectAnswer(int number, String answer) {
        return fizzBuzz(number).equals(answer);
    }
}
