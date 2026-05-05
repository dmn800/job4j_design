package ru.job4j.ood.isp.ex1;

public class SecurityGuard implements Worker {
    @Override
    public void workWithClients() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void guardOffice() {
        System.out.println("Охраняет офис");
    }
}
