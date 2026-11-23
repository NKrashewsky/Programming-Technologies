package by.bsu.krashevsky.chef.creator;

import by.bsu.krashevsky.chef.entity.RootVegetable;
import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.parser.VegetableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создает корнеплоды. Некорректный доп. параметр заменяется значением по умолчанию.
 */
public class RootVegetableCreator extends VegetableCreator {

    private static final Logger LOGGER = LogManager.getLogger(RootVegetableCreator.class);
    private static final boolean DEFAULT_NEEDS_PEELING = true;

    @Override
    public Vegetable factoryMethod(VegetableData data) {
        boolean needsPeeling = DEFAULT_NEEDS_PEELING;
        if ("true".equalsIgnoreCase(data.extra())) {
            needsPeeling = true;
        } else if ("false".equalsIgnoreCase(data.extra())) {
            needsPeeling = false;
        } else {
            LOGGER.warn("Некорректный параметр очистки '{}' у '{}', взято значение по умолчанию: {}",
                    data.extra(), data.name(), DEFAULT_NEEDS_PEELING);
        }
        return new RootVegetable(data.name(), data.weight(), data.caloriesPer100g(),
                needsPeeling);
    }
}
