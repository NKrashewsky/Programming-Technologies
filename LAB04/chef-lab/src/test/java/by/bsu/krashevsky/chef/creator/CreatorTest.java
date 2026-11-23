package by.bsu.krashevsky.chef.creator;

import by.bsu.krashevsky.chef.entity.FruitVegetable;
import by.bsu.krashevsky.chef.entity.LeafyVegetable;
import by.bsu.krashevsky.chef.entity.RootVegetable;
import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.parser.VegetableData;
import by.bsu.krashevsky.chef.type.VegetableType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CreatorTest {

    private static VegetableData data(VegetableType type, String extra) {
        return new VegetableData(type, "Тест", 100, 20, extra);
    }

    @DataProvider
    public Object[][] rootExtras() {
        return new Object[][]{{"true", true}, {"FALSE", false}, {"maybe", true}, {"", true}};
    }

    @Test(dataProvider = "rootExtras")
    public void rootCreator(String extra, boolean expected) {
        Vegetable v = new RootVegetableCreator().factoryMethod(data(VegetableType.ROOT, extra));
        assertTrue(v instanceof RootVegetable);
        assertEquals(((RootVegetable) v).isNeedsPeeling(), expected);
        assertEquals(v.getName(), "Тест");
    }

    @DataProvider
    public Object[][] leafyExtras() {
        return new Object[][]{{"3", 3}, {"0", 0}, {"-1", 1}, {"abc", 1}};
    }

    @Test(dataProvider = "leafyExtras")
    public void leafyCreator(String extra, int expected) {
        Vegetable v = new LeafyVegetableCreator().factoryMethod(data(VegetableType.LEAFY, extra));
        assertTrue(v instanceof LeafyVegetable);
        assertEquals(((LeafyVegetable) v).getFreshnessDays(), expected);
    }

    @DataProvider
    public Object[][] fruitExtras() {
        return new Object[][]{{"7", 7}, {"1", 1}, {"10", 10}, {"0", 5}, {"11", 5}, {"abc", 5}};
    }

    @Test(dataProvider = "fruitExtras")
    public void fruitCreator(String extra, int expected) {
        Vegetable v = new FruitVegetableCreator().factoryMethod(data(VegetableType.FRUIT, extra));
        assertTrue(v instanceof FruitVegetable);
        assertEquals(((FruitVegetable) v).getRipenessLevel(), expected);
    }

    @Test
    public void providerReturnsCorrectCreator() {
        VegetableCreatorProvider provider = new VegetableCreatorProvider();
        assertTrue(provider.getCreator(VegetableType.ROOT) instanceof RootVegetableCreator);
        assertTrue(provider.getCreator(VegetableType.LEAFY) instanceof LeafyVegetableCreator);
        assertTrue(provider.getCreator(VegetableType.FRUIT) instanceof FruitVegetableCreator);
        assertFalse(provider.getCreator(VegetableType.ROOT) instanceof FruitVegetableCreator);
    }
}
