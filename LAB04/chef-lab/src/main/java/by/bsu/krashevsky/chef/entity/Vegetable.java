package by.bsu.krashevsky.chef.entity;

/**
 * Базовый класс овоща.
 */
public abstract class Vegetable {

    private static final double GRAMS_IN_PORTION = 100.0;

    private final String name;
    private final double weight;
    private final double caloriesPer100g;

    protected Vegetable(String name, double weight, double caloriesPer100g) {
        this.name = name;
        this.weight = weight;
        this.caloriesPer100g = caloriesPer100g;
    }

    public String getName() {
        return name;
    }

    /**
     * @return вес овоща в граммах
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return калорийность на 100 г
     */
    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    /**
     * @return калорийность всего овоща с учетом его веса (ккал)
     */
    public double getTotalCalories() {
        return weight * caloriesPer100g / GRAMS_IN_PORTION;
    }

    @Override
    public String toString() {
        return String.format("%s{name='%s', weight=%.1f, caloriesPer100g=%.1f",
                getClass().getSimpleName(), name, weight, caloriesPer100g);
    }
}
