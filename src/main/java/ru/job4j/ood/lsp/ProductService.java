package ru.job4j.ood.lsp;

/*
Ослабление постусловия - Родитель гарантирует, если товар просрочен,
его нельзя продать. Наследник убирает эту гарантию
 */


import java.time.LocalDate;

class ProductService {
    public boolean canSell(LocalDate expireDate) {
        if (expireDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Товар просрочен");
        }
        return true;
    }
}

class MarketProductService extends ProductService {
    @Override
    public boolean canSell(LocalDate expireDate) {
        return true;
    }
}
