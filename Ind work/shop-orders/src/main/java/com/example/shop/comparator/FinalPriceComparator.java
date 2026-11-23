package com.example.shop.comparator;

import com.example.shop.entity.Product;
import java.util.Comparator;

/**
 * Сравнение товаров по итоговой цене.
 */
public class FinalPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product first, Product second) {
        return Double.compare(first.getFinalPrice(), second.getFinalPrice());
    }
}
