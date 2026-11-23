package by.bsu.krashevsky.chef.entity;

/**
 * Листовой овощ (салат, шпинат, капуста...).
 */
public class LeafyVegetable extends Vegetable {

    private final int freshnessDays;

    public LeafyVegetable(String name, double weight, double caloriesPer100g,
                          int freshnessDays) {
        super(name, weight, caloriesPer100g);
        this.freshnessDays = freshnessDays;
    }

    /**
     * @return сколько дней прошло с момента сбора
     */
    public int getFreshnessDays() {
        return freshnessDays;
    }

    @Override
    public String toString() {
        return super.toString() + ", freshnessDays=" + freshnessDays + "}";
    }
}
