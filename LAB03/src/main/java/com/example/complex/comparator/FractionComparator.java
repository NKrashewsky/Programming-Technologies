package com.example.complex.comparator;

import com.example.complex.entity.Fraction;
import java.util.Comparator;

/**
 * Сравнение дробей (знаменатели всегда положительные).
 */
public class FractionComparator implements Comparator<Fraction> {

    @Override
    public int compare(Fraction first, Fraction second) {
        return Long.compare(
                Math.multiplyExact(first.getNumerator(), second.getDenominator()),
                Math.multiplyExact(second.getNumerator(), first.getDenominator()));
    }
}
