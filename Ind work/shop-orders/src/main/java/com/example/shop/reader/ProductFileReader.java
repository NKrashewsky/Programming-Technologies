package com.example.shop.reader;

import com.example.shop.entity.Product;
import com.example.shop.exception.InvalidProductDataException;
import com.example.shop.factory.ProductCreatorProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Читает товары из текстового файла. Некорректные строки игнорируются.
 */
public class ProductFileReader {

    private static final Logger LOGGER = LogManager.getLogger(ProductFileReader.class);
    private static final String DELIMITER = ";";
    private static final String COMMENT = "#";

    private final ProductCreatorProvider provider = new ProductCreatorProvider();

    public List<Product> read(Path path) throws IOException {
        List<Product> products = new ArrayList<>();
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
                Product product = provider.getCreator(params[0]).createProduct(params);
                products.add(product);
                LOGGER.debug("Created {}", product);
            } catch (InvalidProductDataException e) {
                LOGGER.error("Line {} ignored: {} ({})", i + 1, e.getMessage(), line);
            }
        }
        LOGGER.info("Created {} products", products.size());
        return products;
    }
}
