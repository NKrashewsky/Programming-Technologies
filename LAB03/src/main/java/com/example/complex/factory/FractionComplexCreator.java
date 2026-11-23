package com.example.complex.factory;

import com.example.complex.entity.Complex;
import com.example.complex.exception.InvalidComplexDataException;

/**
 * Создаёт число из двух дробей: FRACTION;reNum;reDen;imNum;imDen.
 */
public class FractionComplexCreator extends ComplexCreator {

    private static final int REQUIRED_PARAMS = 5;

    @Override
    public Complex createComplex(String[] params) throws InvalidComplexDataException {
        checkLength(params, REQUIRED_PARAMS);
        return new Complex(parseFraction(params[1], params[2]), parseFraction(params[3], params[4]));
    }
}
