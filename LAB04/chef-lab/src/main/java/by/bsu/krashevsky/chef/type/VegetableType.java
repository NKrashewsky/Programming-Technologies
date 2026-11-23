package by.bsu.krashevsky.chef.type;

import java.util.Optional;

/**
 * Тип овоща (определяет, какой класс будет создан).
 */
public enum VegetableType {
    ROOT,
    LEAFY,
    FRUIT;

    /**
     * Разбирает строку в тип овоща без учета регистра.
     *
     * @param value строка с названием типа
     * @return тип или {@link Optional#empty()}, если тип неизвестен
     */
    public static Optional<VegetableType> parse(String value) {
        if (value == null) {
            return Optional.empty();
        }
        for (VegetableType type : values()) {
            if (type.name().equalsIgnoreCase(value.trim())) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
