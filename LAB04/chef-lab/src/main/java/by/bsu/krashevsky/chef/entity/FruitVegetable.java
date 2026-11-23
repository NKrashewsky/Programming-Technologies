package by.bsu.krashevsky.chef.entity;

/**
 * Плодовый овощ (помидор, огурец, перец...).
 */
public class FruitVegetable extends Vegetable {

    private final int ripenessLevel;

    public FruitVegetable(String name, double weight, double caloriesPer100g,
                          int ripenessLevel) {
        super(name, weight, caloriesPer100g);
        this.ripenessLevel = ripenessLevel;
    }

    /**
     * @return степень спелости от 1 до 10
     */
    public int getRipenessLevel() {
        return ripenessLevel;
    }

    @Override
    public String toString() {
        return super.toString() + ", ripenessLevel=" + ripenessLevel + "}";
    }
}
