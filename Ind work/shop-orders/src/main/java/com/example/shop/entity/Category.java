package com.example.shop.entity;

import java.util.Optional;

/**
 * Категория товара.
 */
public enum Category {
    ELECTRONICS,
    FOOD,
    CLOTHING,
    BOOKS,
    OTHER;

    /**
     * Безопасно разбирает категорию из строки.
     *
     * @param value строковое значение
     * @return категория или пустой Optional, если значение некорректно
     */
    public static Optional<Category> parse(String value) {
        if (value == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(valueOf(value.trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
