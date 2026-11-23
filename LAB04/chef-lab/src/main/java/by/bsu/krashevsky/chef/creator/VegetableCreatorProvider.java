package by.bsu.krashevsky.chef.creator;

import by.bsu.krashevsky.chef.type.VegetableType;

/**
 * Возвращает подходящий Creator для типа овоща.
 */
public class VegetableCreatorProvider {

    /**
     * @param type тип овоща
     * @return создатель овощей данного типа
     */
    public VegetableCreator getCreator(VegetableType type) {
        return switch (type) {
            case ROOT -> new RootVegetableCreator();
            case LEAFY -> new LeafyVegetableCreator();
            case FRUIT -> new FruitVegetableCreator();
        };
    }
}
