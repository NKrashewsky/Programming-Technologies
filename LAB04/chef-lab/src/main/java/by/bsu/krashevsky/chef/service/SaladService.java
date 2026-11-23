package by.bsu.krashevsky.chef.service;

import by.bsu.krashevsky.chef.entity.Salad;
import by.bsu.krashevsky.chef.entity.Vegetable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Бизнес-логика работы с салатом.
 */
public class SaladService {

    private static final Logger LOGGER = LogManager.getLogger(SaladService.class);

    /**
     * @param salad салат
     * @return общая калорийность салата (ккал)
     */
    public double calculateCalories(Salad salad) {
        double total = 0;
        for (Vegetable vegetable : salad.getVegetables()) {
            total += vegetable.getTotalCalories();
        }
        LOGGER.debug("Калорийность салата '{}': {}", salad.getName(), total);
        return total;
    }

    /**
     * @param salad      салат
     * @param comparator критерий сортировки
     * @return новый отсортированный список овощей (салат не изменяется)
     */
    public List<Vegetable> sort(Salad salad, Comparator<Vegetable> comparator) {
        List<Vegetable> sorted = new ArrayList<>(salad.getVegetables());
        sorted.sort(comparator);
        return sorted;
    }

    /**
     * Ищет овощи, калорийность которых на 100 г лежит в диапазоне [min; max].
     *
     * @param salad салат
     * @param min   нижняя граница
     * @param max   верхняя граница
     * @return найденные овощи
     */
    public List<Vegetable> findByCaloriesRange(Salad salad, double min, double max) {
        List<Vegetable> found = new ArrayList<>();
        for (Vegetable vegetable : salad.getVegetables()) {
            double calories = vegetable.getCaloriesPer100g();
            if (calories >= min && calories <= max) {
                found.add(vegetable);
            }
        }
        LOGGER.debug("Найдено овощей в диапазоне [{}; {}]: {}", min, max, found.size());
        return found;
    }
}
