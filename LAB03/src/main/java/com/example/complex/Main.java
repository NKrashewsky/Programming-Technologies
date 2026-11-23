package com.example.complex;

import com.example.complex.entity.Complex;
import com.example.complex.reader.ComplexFileReader;
import com.example.complex.service.ComplexService;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Точка входа. Меню не реализовано по условию задачи.
 */
public final class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String DEFAULT_FILE = "data/complex.txt";

    private Main() {
    }

    public static void main(String[] args) {
        Path path = Path.of(args.length > 0 ? args[0] : DEFAULT_FILE);
        try {
            List<Complex> numbers = new ComplexFileReader().read(path);
            LOGGER.info("n = {}", numbers.size());
            LOGGER.info("Numbers: {}", numbers);

            ComplexService service = new ComplexService();
            LOGGER.info("Sum: {}", service.sum(numbers));
            LOGGER.info("Product: {}", service.product(numbers));
            service.maxByModulus(numbers).ifPresent(c -> LOGGER.info("Max modulus: {}", c));
            LOGGER.info("Sorted by modulus: {}", service.sortByModulus(numbers));
        } catch (IOException e) {
            LOGGER.error("Cannot read file {}", path, e);
        } catch (ArithmeticException e) {
            LOGGER.error("Arithmetic overflow while calculating", e);
        }
    }
}
