package by.bsu.krashevsky.chef.parser;

import by.bsu.krashevsky.chef.type.VegetableType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * Разбор и проверка строки вида {@code ТИП;название;вес;калорийность;доп.параметр}.
 * Некорректные строки игнорируются.
 */
public class VegetableLineParser {

    private static final Logger LOGGER = LogManager.getLogger(VegetableLineParser.class);

    private static final String DELIMITER = ";";
    private static final String COMMENT_PREFIX = "#";
    private static final String NAME_REGEX = "[\\p{L} \\-]+";
    private static final int MIN_FIELDS = 4;
    private static final int EXTRA_INDEX = 4;
    private static final double MAX_WEIGHT = 5000.0;
    private static final double MAX_CALORIES = 900.0;

    /**
     * @param line строка файла
     * @return данные овоща или {@link Optional#empty()}, если строка пустая,
     *         комментарий или содержит некорректные данные
     */
    public Optional<VegetableData> parse(String line) {
        if (line == null || line.isBlank() || line.trim().startsWith(COMMENT_PREFIX)) {
            return Optional.empty();
        }
        String[] parts = line.split(DELIMITER, -1);
        if (parts.length < MIN_FIELDS) {
            LOGGER.warn("Строка проигнорирована (мало полей): '{}'", line);
            return Optional.empty();
        }
        Optional<VegetableType> type = VegetableType.parse(parts[0]);
        if (type.isEmpty()) {
            LOGGER.warn("Строка проигнорирована (неизвестный тип): '{}'", line);
            return Optional.empty();
        }
        String name = parts[1].trim();
        if (!name.matches(NAME_REGEX)) {
            LOGGER.warn("Строка проигнорирована (некорректное название): '{}'", line);
            return Optional.empty();
        }
        Double weight = parseInRange(parts[2], MAX_WEIGHT);
        Double calories = parseInRange(parts[3], MAX_CALORIES);
        if (weight == null || calories == null) {
            LOGGER.warn("Строка проигнорирована (некорректный вес или калорийность): '{}'",
                    line);
            return Optional.empty();
        }
        String extra = parts.length > EXTRA_INDEX ? parts[EXTRA_INDEX].trim() : "";
        LOGGER.debug("Строка успешно разобрана: '{}'", line);
        return Optional.of(new VegetableData(type.get(), name, weight, calories, extra));
    }

    private Double parseInRange(String value, double max) {
        try {
            double number = Double.parseDouble(value.trim());
            return number > 0 && number <= max ? number : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
