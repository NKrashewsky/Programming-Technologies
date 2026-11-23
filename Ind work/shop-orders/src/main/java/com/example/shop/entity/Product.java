package com.example.shop.entity;

import java.util.Objects;

/**
 * Обычный товар. Дополнительное поле - {@link Category}.
 */
public class Product {

    private final long id;
    private final String name;
    private final double price;
    private final Category category;

    public Product(long id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * Итоговая стоимость товара. Для обычного товара равна базовой цене.
     *
     * @return стоимость к оплате
     */
    public double getFinalPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + id + ", name='" + name + '\''
                + ", price=" + price + ", category=" + category + '}';
    }
}
