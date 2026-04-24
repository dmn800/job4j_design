package ru.job4j.ood.lsp;

/*
Нарушение инварианта - только Родитель хранит корректный процент
скидки, а Наследник переопределяет установку скидки и убирает ее.
 */

class DiscountCard {
    protected int discount;

    public DiscountCard(int discount) {
        setDiscount(discount);
    }

    public void setDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100");
        }
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}

class VipDiscountCard extends DiscountCard {
    public VipDiscountCard(int discount) {
        super(discount);
    }

    @Override
    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
