package com.example.shop.entity;

/**
 * Товар со скидкой. Расширяет {@link Product} полем discountPercent.
 */
public class DiscountedProduct extends Product {

    private static final double PERCENT = 100.0;

    private final double discountPercent;

    public DiscountedProduct(long id, String name, double price,
                             Category category, double discountPercent) {
        super(id, name, price, category);
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Размер скидки в денежном выражении.
     *
     * @return сумма скидки
     */
    public double getDiscountAmount() {
        return getPrice() * discountPercent / PERCENT;
    }

    @Override
    public double getFinalPrice() {
        return getPrice() - getDiscountAmount();
    }

    @Override
    public String toString() {
        return super.toString() + "{discountPercent=" + discountPercent + '}';
    }
}
