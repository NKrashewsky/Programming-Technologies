package com.example.shop.service;

import com.example.shop.comparator.FinalPriceComparator;
import com.example.shop.comparator.NameComparator;
import com.example.shop.entity.Category;
import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Бизнес-логика над заказом.
 */
public class OrderService {

    private final Comparator<Product> priceComparator = new FinalPriceComparator();

    /** Общая стоимость заказа с учётом скидок. */
    public double totalCost(Order order) {
        return products(order).stream().mapToDouble(Product::getFinalPrice).sum();
    }

    /** Самый дорогой товар заказа. */
    public Optional<Product> mostExpensive(Order order) {
        return products(order).stream().max(priceComparator);
    }

    /** Товары, итоговая цена которых меньше заданной. */
    public List<Product> cheaperThan(Order order, double limit) {
        return products(order).stream()
                .filter(p -> p.getFinalPrice() < limit)
                .toList();
    }

    /** Средняя итоговая цена товара в заказе (0 для пустого заказа). */
    public double averagePrice(Order order) {
        return products(order).stream().mapToDouble(Product::getFinalPrice).average().orElse(0);
    }

    /** Сколько клиент сэкономил благодаря скидкам. */
    public double totalSavings(Order order) {
        return products(order).stream()
                .mapToDouble(p -> p.getPrice() - p.getFinalPrice())
                .sum();
    }

    /** Товары заданной категории. */
    public List<Product> filterByCategory(Order order, Category category) {
        return products(order).stream()
                .filter(p -> p.getCategory() == category)
                .toList();
    }

    /** Товары, отсортированные по возрастанию итоговой цены. */
    public List<Product> sortByFinalPrice(Order order) {
        List<Product> sorted = new ArrayList<>(products(order));
        sorted.sort(priceComparator);
        return sorted;
    }

    /** Товары, отсортированные по названию. */
    public List<Product> sortByName(Order order) {
        List<Product> sorted = new ArrayList<>(products(order));
        sorted.sort(new NameComparator());
        return sorted;
    }

    private List<Product> products(Order order) {
        return Objects.requireNonNull(order, "order must not be null").getProducts();
    }
}
