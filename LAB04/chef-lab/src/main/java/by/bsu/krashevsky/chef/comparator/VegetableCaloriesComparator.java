package by.bsu.krashevsky.chef.comparator;

import by.bsu.krashevsky.chef.entity.Vegetable;

import java.util.Comparator;

/**
 * Сравнение овощей по калорийности на 100 г.
 */
public class VegetableCaloriesComparator implements Comparator<Vegetable> {

    @Override
    public int compare(Vegetable first, Vegetable second) {
        return Double.compare(first.getCaloriesPer100g(), second.getCaloriesPer100g());
    }
}
