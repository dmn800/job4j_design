package ru.job4j.ood.srp;

/*
Класс и формирует отчёт, и выводит его на экран.
Причина нарушения принципа: генерация содержимого и
вывод результата — это разные обязанности.
*/

public class Report {
    public String generateReport() {
        return "Отчёт по продажам";
    }

    public void printReport() {
        System.out.println(generateReport());
    }
}
