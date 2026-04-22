package ru.job4j.ood.srp;

/*
Класс отвечает и за обработку заказа, и за отправку уведомления.
Причина нарушения принципа: изменение логики обработки заказа и
изменение логики уведомлений — это две разные причины для изменения класса.
*/

public class Order {
    private int id;
    private double amount;

    public Order(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public void processOrder() {
        System.out.println("Обработка заказа " + id);
    }

    public void sendEmailNotification() {
        System.out.println("Отправка письма по заказу " + id);
    }
}
