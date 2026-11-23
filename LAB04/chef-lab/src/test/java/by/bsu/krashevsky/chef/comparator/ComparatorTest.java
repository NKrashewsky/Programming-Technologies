package by.bsu.krashevsky.chef.comparator;

import by.bsu.krashevsky.chef.entity.RootVegetable;
import by.bsu.krashevsky.chef.entity.Vegetable;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ComparatorTest {

    private final Vegetable light = new RootVegetable("Абрикос", 50, 10, true);
    private final Vegetable heavy = new RootVegetable("Яблоко", 150, 40, true);

    @Test
    public void caloriesComparator() {
        VegetableCaloriesComparator c = new VegetableCaloriesComparator();
        assertTrue(c.compare(light, heavy) < 0);
        assertTrue(c.compare(heavy, light) > 0);
        assertEquals(c.compare(light, light), 0);
    }

    @Test
    public void weightComparator() {
        VegetableWeightComparator c = new VegetableWeightComparator();
        assertTrue(c.compare(light, heavy) < 0);
        assertTrue(c.compare(heavy, light) > 0);
        assertEquals(c.compare(heavy, heavy), 0);
    }

    @Test
    public void nameComparator() {
        VegetableNameComparator c = new VegetableNameComparator();
        assertTrue(c.compare(light, heavy) < 0);
        assertTrue(c.compare(heavy, light) > 0);
        assertEquals(c.compare(light, light), 0);
    }
}
