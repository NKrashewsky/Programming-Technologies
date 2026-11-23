package com.example.shop.factory;

import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.exception.InvalidProductDataException;
import com.example.shop.validator.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creator из паттерна Factory Method.
 * Параметры: type;id;name;price;category[;discountPercent]
 */
public abstract class ProductCreator {

    protected static final int ID_INDEX = 1;
    protected static final int NAME_INDEX = 2;
    protected static final int PRICE_INDEX = 3;
    protected static final int CATEGORY_INDEX = 4;

    private static final Logger LOGGER = LogManager.getLogger(ProductCreator.class);

    protected final ProductValidator validator = new ProductValidator();

    /**
     * Фабричный метод.
     *
     * @param params параметры товара из файла
     * @return созданный товар
     * @throws InvalidProductDataException если данные нельзя исправить
     */
    public abstract Product createProduct(String[] params) throws InvalidProductDataException;

    protected void checkLength(String[] params, int required) throws InvalidProductDataException {
        if (params.length < required) {
            throw new InvalidProductDataException("Expected " + required
                    + " parameters but got " + params.length);
        }
    }

    protected long parseId(String raw) throws InvalidProductDataException {
        try {
            long id = Long.parseLong(raw);
            if (!validator.isValidId(id)) {
                throw new InvalidProductDataException("Invalid id: " + raw);
            }
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidProductDataException("Id is not a number: " + raw);
        }
    }

    protected String parseName(String raw) throws InvalidProductDataException {
        if (!validator.isValidName(raw)) {
            throw new InvalidProductDataException("Name is empty");
        }
        return raw;
    }

    protected double parsePrice(String raw) throws InvalidProductDataException {
        try {
            double price = Double.parseDouble(raw);
            if (!validator.isValidPrice(price)) {
                throw new InvalidProductDataException("Invalid price: " + raw);
            }
            return price;
        } catch (NumberFormatException e) {
            throw new InvalidProductDataException("Price is not a number: " + raw);
        }
    }

    /**
     * Некорректная категория заменяется значением по умолчанию.
     */
    protected Category parseCategory(String raw) {
        return Category.parse(raw).orElseGet(() -> {
            LOGGER.warn("Unknown category '{}', default OTHER is used", raw);
            return Category.OTHER;
        });
    }
}
