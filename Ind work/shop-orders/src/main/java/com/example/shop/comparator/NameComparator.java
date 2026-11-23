package com.example.shop.comparator;

import com.example.shop.entity.Product;
import java.util.Comparator;

/**
 * Сравнение товаров по названию.
 */
public class NameComparator implements Comparator<Product> {

    @Override
    public int compare(Product first, Product second) {
        return first.getName().compareToIgnoreCase(second.getName());
    }
}
