package by.bsu.krashevsky.chef.comparator;

import by.bsu.krashevsky.chef.entity.Vegetable;

import java.util.Comparator;

/**
 * Сравнение овощей по весу.
 */
public class VegetableWeightComparator implements Comparator<Vegetable> {

    @Override
    public int compare(Vegetable first, Vegetable second) {
        return Double.compare(first.getWeight(), second.getWeight());
    }
}
