package com.example.shop.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Заказ - набор товаров.
 */
public class Order {

    private final long id;
    private final List<Product> products = new ArrayList<>();

    public Order(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
