package com.example.complex.factory;

import com.example.complex.exception.InvalidComplexDataException;
import java.util.Map;

/**
 * Выбирает Creator по типу записи.
 */
public class ComplexCreatorProvider {

    private final Map<String, ComplexCreator> creators = Map.of(
            "FRACTION", new FractionComplexCreator(),
            "INTEGER", new IntegerComplexCreator());

    public ComplexCreator getCreator(String type) throws InvalidComplexDataException {
        ComplexCreator creator = type == null ? null : creators.get(type.trim().toUpperCase());
        if (creator == null) {
            throw new InvalidComplexDataException("Unknown type: " + type);
        }
        return creator;
    }
}
