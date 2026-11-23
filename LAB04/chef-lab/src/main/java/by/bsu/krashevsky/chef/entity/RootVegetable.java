package by.bsu.krashevsky.chef.entity;

/**
 * Корнеплод (морковь, картофель, свекла...).
 */
public class RootVegetable extends Vegetable {

    private final boolean needsPeeling;

    public RootVegetable(String name, double weight, double caloriesPer100g,
                         boolean needsPeeling) {
        super(name, weight, caloriesPer100g);
        this.needsPeeling = needsPeeling;
    }

    public boolean isNeedsPeeling() {
        return needsPeeling;
    }

    @Override
    public String toString() {
        return super.toString() + ", needsPeeling=" + needsPeeling + "}";
    }
}
