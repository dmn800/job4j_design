package ru.job4j.ood.isp.ex2;

public class CashPayment implements PaymentTool {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата наличными: " + amount);
    }

    @Override
    public void refundToCard(String cardNumber) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printReceipt() {
        System.out.println("Печать чека");
    }
}
