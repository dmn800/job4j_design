package ru.job4j.ood.isp.ex2;

public interface PaymentTool {

    void pay(double amount);

    void refundToCard(String cardNumber);

    void printReceipt();

}
