package ru.job4j.ood.ocp;

/*
Нарушение принципа OCP, когда при добавлении нового способа оплаты (например, Crypto)
необходимо изменять метод pay()
*/

public class PaymentService {
    public void pay(String type) {
        if (type.equals("Card")) {
            System.out.println("Логика покупки по карте");
        } else if (type.equals("Paypal")) {
            System.out.println("Логика покупки через Paypal");
        }
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        service.pay("Card");
        service.pay("Paypal");
    }
}
