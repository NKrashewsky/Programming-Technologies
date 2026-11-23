package com.example.complex.factory;

import com.example.complex.entity.Complex;
import com.example.complex.entity.Fraction;
import com.example.complex.exception.InvalidComplexDataException;
import com.example.complex.validator.ComplexValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creator из паттерна Factory Method. Первый параметр строки - тип записи.
 */
public abstract class ComplexCreator {

    private static final Logger LOGGER = LogManager.getLogger(ComplexCreator.class);
    private static final long DEFAULT_DENOMINATOR = 1;

    protected final ComplexValidator validator = new ComplexValidator();

    /**
     * Фабричный метод.
     *
     * @param params параметры строки файла (первый элемент - тип)
     * @return комплексное число
     * @throws InvalidComplexDataException если данные нельзя исправить
     */
    public abstract Complex createComplex(String[] params) throws InvalidComplexDataException;

    protected void checkLength(String[] params, int required) throws InvalidComplexDataException {
        if (params.length < required) {
            throw new InvalidComplexDataException("Expected " + required
                    + " parameters but got " + params.length);
        }
    }

    protected long parseLong(String raw) throws InvalidComplexDataException {
        try {
            return Long.parseLong(raw);
        } catch (NumberFormatException e) {
            throw new InvalidComplexDataException("Not an integer: " + raw);
        }
    }

    /**
     * Нулевой знаменатель заменяется значением по умолчанию (1).
     */
    protected Fraction parseFraction(String numerator, String denominator)
            throws InvalidComplexDataException {
        long num = parseLong(numerator);
        long den = parseLong(denominator);
        if (!validator.isValidDenominator(den)) {
            LOGGER.warn("Zero denominator, default {} is used", DEFAULT_DENOMINATOR);
            den = DEFAULT_DENOMINATOR;
        }
        return new Fraction(num, den);
    }
}
