package com.example.shop.factory;

import com.example.shop.entity.DiscountedProduct;
import com.example.shop.entity.Product;
import com.example.shop.exception.InvalidProductDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создаёт товары со скидкой.
 */
public class DiscountedProductCreator extends ProductCreator {

    private static final Logger LOGGER = LogManager.getLogger(DiscountedProductCreator.class);
    private static final int REQUIRED_PARAMS = 6;
    private static final int DISCOUNT_INDEX = 5;
    private static final double DEFAULT_DISCOUNT = 0.0;

    @Override
    public Product createProduct(String[] params) throws InvalidProductDataException {
        checkLength(params, REQUIRED_PARAMS);
        return new DiscountedProduct(parseId(params[ID_INDEX]), parseName(params[NAME_INDEX]),
                parsePrice(params[PRICE_INDEX]), parseCategory(params[CATEGORY_INDEX]),
                parseDiscount(params[DISCOUNT_INDEX]));
    }

    /**
     * Некорректная скидка заменяется значением по умолчанию (0%).
     */
    private double parseDiscount(String raw) {
        try {
            double discount = Double.parseDouble(raw);
            if (validator.isValidDiscount(discount)) {
                return discount;
            }
        } catch (NumberFormatException e) {
            LOGGER.debug("Discount is not a number: {}", raw);
        }
        LOGGER.warn("Invalid discount '{}', default {} is used", raw, DEFAULT_DISCOUNT);
        return DEFAULT_DISCOUNT;
    }
}
