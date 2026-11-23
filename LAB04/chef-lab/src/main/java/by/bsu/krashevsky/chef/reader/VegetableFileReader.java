package by.bsu.krashevsky.chef.reader;

import by.bsu.krashevsky.chef.creator.VegetableCreatorProvider;
import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.exception.VegetableException;
import by.bsu.krashevsky.chef.parser.VegetableData;
import by.bsu.krashevsky.chef.parser.VegetableLineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Читает файл .txt и создает из его корректных строк овощи.
 */
public class VegetableFileReader {

    private static final Logger LOGGER = LogManager.getLogger(VegetableFileReader.class);

    private final VegetableLineParser parser = new VegetableLineParser();
    private final VegetableCreatorProvider provider = new VegetableCreatorProvider();

    /**
     * @param path путь к файлу
     * @return список созданных овощей (некорректные строки пропускаются)
     * @throws VegetableException если файл не удалось прочитать
     */
    public List<Vegetable> read(Path path) throws VegetableException {
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Не удалось прочитать файл {}", path, e);
            throw new VegetableException("Не удалось прочитать файл " + path, e);
        }
        List<Vegetable> vegetables = new ArrayList<>();
        for (String line : lines) {
            Optional<VegetableData> data = parser.parse(line);
            data.ifPresent(d -> vegetables.add(provider.getCreator(d.type()).factoryMethod(d)));
        }
        LOGGER.info("Из файла {} создано овощей: {}", path, vegetables.size());
        return vegetables;
    }
}
