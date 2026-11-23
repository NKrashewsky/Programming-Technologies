package by.bsu.krashevsky.chef;

import by.bsu.krashevsky.chef.comparator.VegetableCaloriesComparator;
import by.bsu.krashevsky.chef.comparator.VegetableWeightComparator;
import by.bsu.krashevsky.chef.entity.Salad;
import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.exception.VegetableException;
import by.bsu.krashevsky.chef.reader.VegetableFileReader;
import by.bsu.krashevsky.chef.service.SaladService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;

/**
 * Точка входа: читает овощи из файла, делает салат, считает калорийность,
 * сортирует и ищет овощи по диапазону калорийности.
 */
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String DEFAULT_FILE = "data/vegetables.txt";
    private static final double MIN_CALORIES = 20;
    private static final double MAX_CALORIES = 45;

    public static void main(String[] args) {
        Path path = Path.of(args.length > 0 ? args[0] : DEFAULT_FILE);
        try {
            List<Vegetable> vegetables = new VegetableFileReader().read(path);
            Salad salad = new Salad("Овощной салат");
            vegetables.forEach(salad::addVegetable);

            SaladService service = new SaladService();
            LOGGER.info("Салат: {}", salad);
            LOGGER.info("Общая калорийность: {} ккал", service.calculateCalories(salad));

            LOGGER.info("Сортировка по калорийности:");
            service.sort(salad, new VegetableCaloriesComparator())
                    .forEach(v -> LOGGER.info("  {}", v));
            LOGGER.info("Сортировка по весу:");
            service.sort(salad, new VegetableWeightComparator())
                    .forEach(v -> LOGGER.info("  {}", v));

            LOGGER.info("Овощи с калорийностью {}-{} ккал/100г:", MIN_CALORIES, MAX_CALORIES);
            service.findByCaloriesRange(salad, MIN_CALORIES, MAX_CALORIES)
                    .forEach(v -> LOGGER.info("  {}", v));
        } catch (VegetableException e) {
            LOGGER.fatal("Работа завершена из-за ошибки: {}", e.getMessage());
        }
    }
}
