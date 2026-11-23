package by.bsu.krashevsky.chef.parser;

import by.bsu.krashevsky.chef.type.VegetableType;

/**
 * Проверенные данные одной строки файла, необходимые для создания овоща.
 *
 * @param type            тип овоща
 * @param name            название
 * @param weight          вес в граммах
 * @param caloriesPer100g калорийность на 100 г
 * @param extra           дополнительный параметр (зависит от типа, может быть пустым)
 */
public record VegetableData(VegetableType type, String name, double weight,
                            double caloriesPer100g, String extra) {
}
