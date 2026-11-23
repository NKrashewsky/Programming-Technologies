package by.bsu.krashevsky.chef.creator;

import by.bsu.krashevsky.chef.entity.FruitVegetable;
import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.parser.VegetableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создает плодовые овощи. Некорректный доп. параметр заменяется значением по умолчанию.
 */
public class FruitVegetableCreator extends VegetableCreator {

    private static final Logger LOGGER = LogManager.getLogger(FruitVegetableCreator.class);
    private static final int DEFAULT_RIPENESS = 5;
    private static final int MIN_RIPENESS = 1;
    private static final int MAX_RIPENESS = 10;

    @Override
    public Vegetable factoryMethod(VegetableData data) {
        int ripeness = DEFAULT_RIPENESS;
        try {
            int parsed = Integer.parseInt(data.extra());
            if (parsed >= MIN_RIPENESS && parsed <= MAX_RIPENESS) {
                ripeness = parsed;
            } else {
                warn(data);
            }
        } catch (NumberFormatException e) {
            warn(data);
        }
        return new FruitVegetable(data.name(), data.weight(), data.caloriesPer100g(), ripeness);
    }

    private void warn(VegetableData data) {
        LOGGER.warn("Некорректная спелость '{}' у '{}', взято значение по умолчанию: {}",
                data.extra(), data.name(), DEFAULT_RIPENESS);
    }
}
