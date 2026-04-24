package ru.job4j.ood.lsp;

/*
Усиление предусловия - Родитель ожидает одно поведение,
а наследник получает более жесткие входные условия.
*/

class BankAccount {

    protected double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Некорректная сумма");
        }
        balance -= amount;
    }
}

class PremiumAccount extends BankAccount {

    public PremiumAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance || balance - amount < 1000) {
            throw new IllegalArgumentException("Остаток должен быть не менее 1000");
        }
        balance -= amount;
    }
}
