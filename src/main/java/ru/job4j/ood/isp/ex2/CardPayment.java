package ru.job4j.ood.isp.ex2;

public class CardPayment implements PaymentTool {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата картой: " + amount);
    }

    @Override
    public void refundToCard(String cardNumber) {
        System.out.println("Возврат на карту: " + cardNumber);
    }

    @Override
    public void printReceipt() {
        System.out.println("Печать чека");
    }
}
