package com.example.shop.factory;

import com.example.shop.exception.InvalidProductDataException;
import java.util.Map;

/**
 * Выбирает нужный Creator по типу товара.
 */
public class ProductCreatorProvider {

    private final Map<String, ProductCreator> creators = Map.of(
            "REGULAR", new RegularProductCreator(),
            "DISCOUNTED", new DiscountedProductCreator());

    public ProductCreator getCreator(String type) throws InvalidProductDataException {
        ProductCreator creator = type == null ? null : creators.get(type.trim().toUpperCase());
        if (creator == null) {
            throw new InvalidProductDataException("Unknown product type: " + type);
        }
        return creator;
    }
}
