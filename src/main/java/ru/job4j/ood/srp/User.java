package ru.job4j.ood.srp;

/*
Класс одновременно хранит и представляет данные пользователя,
а также отвечает за сохранение в базу данных.
Причина нарушения принципа: у класса две ответственности —
работа с данными пользователя и работа с хранением.
*/

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getInfo() {
        return name + " " + email;
    }

    public void saveToDatabase() {
        System.out.println("Сохранение пользователя в базу данных");
    }
}
