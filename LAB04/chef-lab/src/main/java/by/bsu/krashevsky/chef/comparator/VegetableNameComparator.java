package by.bsu.krashevsky.chef.comparator;

import by.bsu.krashevsky.chef.entity.Vegetable;

import java.util.Comparator;

/**
 * Сравнение овощей по названию.
 */
public class VegetableNameComparator implements Comparator<Vegetable> {

    @Override
    public int compare(Vegetable first, Vegetable second) {
        return first.getName().compareToIgnoreCase(second.getName());
    }
}
