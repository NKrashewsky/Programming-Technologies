package by.bsu.krashevsky.chef.service;

import by.bsu.krashevsky.chef.comparator.VegetableCaloriesComparator;
import by.bsu.krashevsky.chef.comparator.VegetableWeightComparator;
import by.bsu.krashevsky.chef.entity.FruitVegetable;
import by.bsu.krashevsky.chef.entity.LeafyVegetable;
import by.bsu.krashevsky.chef.entity.RootVegetable;
import by.bsu.krashevsky.chef.entity.Salad;
import by.bsu.krashevsky.chef.entity.Vegetable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SaladServiceTest {

    private static final double DELTA = 1e-9;

    private final SaladService service = new SaladService();
    private Salad salad;
    private Vegetable carrot;
    private Vegetable lettuce;
    private Vegetable tomato;

    @BeforeMethod
    public void setUp() {
        salad = new Salad("Тест");
        carrot = new RootVegetable("Морковь", 200, 41, true);   // 82 ккал
        lettuce = new LeafyVegetable("Салат", 50, 15, 2);       // 7.5 ккал
        tomato = new FruitVegetable("Помидор", 150, 18, 7);     // 27 ккал
        salad.addVegetable(carrot);
        salad.addVegetable(lettuce);
        salad.addVegetable(tomato);
    }

    @Test
    public void calculateCalories() {
        assertEquals(service.calculateCalories(salad), 116.5, DELTA);
    }

    @Test
    public void calculateCaloriesOfEmptySalad() {
        assertEquals(service.calculateCalories(new Salad("Пустой")), 0, DELTA);
    }

    @Test
    public void sortByCalories() {
        List<Vegetable> sorted = service.sort(salad, new VegetableCaloriesComparator());
        assertEquals(sorted, List.of(lettuce, tomato, carrot));
        assertEquals(salad.getVegetables(), List.of(carrot, lettuce, tomato));
    }

    @Test
    public void sortByWeight() {
        List<Vegetable> sorted = service.sort(salad, new VegetableWeightComparator());
        assertEquals(sorted, List.of(lettuce, tomato, carrot));
    }

    @Test
    public void findByRange() {
        assertEquals(service.findByCaloriesRange(salad, 15, 20), List.of(lettuce, tomato));
        assertEquals(service.findByCaloriesRange(salad, 41, 41), List.of(carrot));
    }

    @Test
    public void findByRangeNothing() {
        assertTrue(service.findByCaloriesRange(salad, 100, 200).isEmpty());
    }
}
