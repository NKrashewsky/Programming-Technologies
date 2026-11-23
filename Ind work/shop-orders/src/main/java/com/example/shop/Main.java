package com.example.shop;

import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import com.example.shop.reader.ProductFileReader;
import com.example.shop.service.OrderService;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Точка входа. Меню не реализовано по условию задачи.
 */
public final class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String DEFAULT_FILE = "data/products.txt";
    private static final double PRICE_LIMIT = 100;

    private Main() {
    }

    public static void main(String[] args) {
        Path path = Path.of(args.length > 0 ? args[0] : DEFAULT_FILE);
        try {
            List<Product> products = new ProductFileReader().read(path);
            Order order = new Order(1);
            products.forEach(order::addProduct);

            OrderService service = new OrderService();
            LOGGER.info("Total cost: {}", service.totalCost(order));
            LOGGER.info("Total savings: {}", service.totalSavings(order));
            LOGGER.info("Average price: {}", service.averagePrice(order));
            service.mostExpensive(order)
                    .ifPresent(p -> LOGGER.info("Most expensive: {}", p));
            LOGGER.info("Cheaper than {}: {}", PRICE_LIMIT, service.cheaperThan(order, PRICE_LIMIT));
        } catch (IOException e) {
            LOGGER.error("Cannot read file {}", path, e);
        }
    }
}
