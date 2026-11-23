package com.example.complex.comparator;

import com.example.complex.entity.Complex;
import java.util.Comparator;

/**
 * Сравнение комплексных чисел по модулю (через квадрат модуля).
 */
public class ComplexModulusComparator implements Comparator<Complex> {

    private final FractionComparator fractionComparator = new FractionComparator();

    @Override
    public int compare(Complex first, Complex second) {
        return fractionComparator.compare(first.squaredModulus(), second.squaredModulus());
    }
}
