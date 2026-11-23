package by.bsu.krashevsky.chef.creator;

import by.bsu.krashevsky.chef.entity.LeafyVegetable;
import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.parser.VegetableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создает листовые овощи. Некорректный доп. параметр заменяется значением по умолчанию.
 */
public class LeafyVegetableCreator extends VegetableCreator {

    private static final Logger LOGGER = LogManager.getLogger(LeafyVegetableCreator.class);
    private static final int DEFAULT_FRESHNESS_DAYS = 1;

    @Override
    public Vegetable factoryMethod(VegetableData data) {
        int days = DEFAULT_FRESHNESS_DAYS;
        try {
            int parsed = Integer.parseInt(data.extra());
            if (parsed >= 0) {
                days = parsed;
            } else {
                warn(data);
            }
        } catch (NumberFormatException e) {
            warn(data);
        }
        return new LeafyVegetable(data.name(), data.weight(), data.caloriesPer100g(), days);
    }

    private void warn(VegetableData data) {
        LOGGER.warn("Некорректная свежесть '{}' у '{}', взято значение по умолчанию: {}",
                data.extra(), data.name(), DEFAULT_FRESHNESS_DAYS);
    }
}
