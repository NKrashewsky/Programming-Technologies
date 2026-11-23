package com.example.complex.reader;

import com.example.complex.entity.Complex;
import com.example.complex.exception.InvalidComplexDataException;
import com.example.complex.factory.ComplexCreatorProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Читает комплексные числа из текстового файла. Некорректные строки игнорируются.
 */
public class ComplexFileReader {

    private static final Logger LOGGER = LogManager.getLogger(ComplexFileReader.class);
    private static final String DELIMITER = ";";
    private static final String COMMENT = "#";

    private final ComplexCreatorProvider provider = new ComplexCreatorProvider();

    public List<Complex> read(Path path) throws IOException {
        List<Complex> numbers = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);
        LOGGER.info("Reading {} lines from {}", lines.size(), path);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty() || line.startsWith(COMMENT)) {
                continue;
            }
            String[] params = line.split(DELIMITER, -1);
            for (int j = 0; j < params.length; j++) {
                params[j] = params[j].trim();
            }
            try {
                Complex number = provider.getCreator(params[0]).createComplex(params);
                numbers.add(number);
                LOGGER.debug("Created {}", number);
            } catch (InvalidComplexDataException e) {
                LOGGER.error("Line {} ignored: {} ({})", i + 1, e.getMessage(), line);
            }
        }
        LOGGER.info("Created {} complex numbers", numbers.size());
        return numbers;
    }
}
