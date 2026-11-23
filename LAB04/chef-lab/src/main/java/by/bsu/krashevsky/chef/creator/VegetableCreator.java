package by.bsu.krashevsky.chef.creator;

import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.parser.VegetableData;

/**
 * Creator из паттерна Factory Method.
 */
public abstract class VegetableCreator {

    /**
     * Фабричный метод: создает конкретный вид овоща.
     *
     * @param data проверенные данные
     * @return созданный овощ
     */
    public abstract Vegetable factoryMethod(VegetableData data);
}
