package com.example.complex.factory;

import com.example.complex.entity.Complex;
import com.example.complex.entity.Fraction;
import com.example.complex.exception.InvalidComplexDataException;

/**
 * Создаёт число с целыми частями: INTEGER;re;im.
 */
public class IntegerComplexCreator extends ComplexCreator {

    private static final int REQUIRED_PARAMS = 3;

    @Override
    public Complex createComplex(String[] params) throws InvalidComplexDataException {
        checkLength(params, REQUIRED_PARAMS);
        return new Complex(Fraction.of(parseLong(params[1])), Fraction.of(parseLong(params[2])));
    }
}
