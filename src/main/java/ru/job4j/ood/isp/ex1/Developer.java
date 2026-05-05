package ru.job4j.ood.isp.ex1;

public class Developer implements Worker {
    @Override
    public void workWithClients() {
        System.out.println("Обсуждает требования");
    }

    @Override
    public void writeCode() {
        System.out.println("Пишет код");
    }

    @Override
    public void guardOffice() {
        throw new UnsupportedOperationException();
    }
}
