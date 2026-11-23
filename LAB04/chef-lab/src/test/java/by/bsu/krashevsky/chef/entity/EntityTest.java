package by.bsu.krashevsky.chef.entity;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

public class EntityTest {

    private static final double DELTA = 1e-9;

    @Test
    public void rootVegetable() {
        RootVegetable carrot = new RootVegetable("Морковь", 200, 41, true);
        assertEquals(carrot.getName(), "Морковь");
        assertEquals(carrot.getWeight(), 200, DELTA);
        assertEquals(carrot.getCaloriesPer100g(), 41, DELTA);
        assertEquals(carrot.getTotalCalories(), 82, DELTA);
        assertTrue(carrot.isNeedsPeeling());
        assertTrue(carrot.toString().contains("needsPeeling=true"));
        assertTrue(carrot.toString().startsWith("RootVegetable"));
    }

    @Test
    public void leafyVegetable() {
        LeafyVegetable lettuce = new LeafyVegetable("Салат", 50, 15, 2);
        assertEquals(lettuce.getFreshnessDays(), 2);
        assertEquals(lettuce.getTotalCalories(), 7.5, DELTA);
        assertTrue(lettuce.toString().contains("freshnessDays=2"));
    }

    @Test
    public void fruitVegetable() {
        FruitVegetable tomato = new FruitVegetable("Помидор", 150, 18, 7);
        assertEquals(tomato.getRipenessLevel(), 7);
        assertEquals(tomato.getTotalCalories(), 27, DELTA);
        assertTrue(tomato.toString().contains("ripenessLevel=7"));
    }

    @Test
    public void salad() {
        Salad salad = new Salad("Тест");
        assertEquals(salad.getName(), "Тест");
        assertTrue(salad.getVegetables().isEmpty());
        salad.addVegetable(new RootVegetable("Морковь", 100, 41, false));
        assertEquals(salad.getVegetables().size(), 1);
        assertFalse(((RootVegetable) salad.getVegetables().get(0)).isNeedsPeeling());
        assertTrue(salad.toString().contains("vegetables=1"));
    }

    @Test
    public void saladListIsUnmodifiable() {
        Salad salad = new Salad("Тест");
        expectThrows(UnsupportedOperationException.class,
                () -> salad.getVegetables().add(new RootVegetable("А", 1, 1, true)));
    }
}
