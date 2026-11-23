package com.example.shop.factory;

import com.example.shop.entity.Product;
import com.example.shop.exception.InvalidProductDataException;

/**
 * Создаёт обычные товары.
 */
public class RegularProductCreator extends ProductCreator {

    private static final int REQUIRED_PARAMS = 5;

    @Override
    public Product createProduct(String[] params) throws InvalidProductDataException {
        checkLength(params, REQUIRED_PARAMS);
        return new Product(parseId(params[ID_INDEX]), parseName(params[NAME_INDEX]),
                parsePrice(params[PRICE_INDEX]), parseCategory(params[CATEGORY_INDEX]));
    }
}
