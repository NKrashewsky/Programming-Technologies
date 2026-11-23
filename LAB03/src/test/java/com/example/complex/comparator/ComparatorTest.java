package com.example.complex.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.complex.entity.Complex;
import com.example.complex.entity.Fraction;
import org.junit.jupiter.api.Test;

class ComparatorTest {

    @Test
    void fractionComparator() {
        FractionComparator comparator = new FractionComparator();
        assertTrue(comparator.compare(new Fraction(1, 3), new Fraction(1, 2)) < 0);
        assertTrue(comparator.compare(new Fraction(2, 3), new Fraction(1, 2)) > 0);
        assertEquals(0, comparator.compare(new Fraction(1, 2), new Fraction(2, 4)));
        assertTrue(comparator.compare(new Fraction(-1, 2), new Fraction(1, 3)) < 0);
    }

    @Test
    void complexModulusComparator() {
        ComplexModulusComparator comparator = new ComplexModulusComparator();
        Complex small = new Complex(Fraction.of(1), Fraction.ZERO);
        Complex big = new Complex(Fraction.of(3), Fraction.of(4));
        Complex sameAsBig = new Complex(Fraction.of(-4), Fraction.of(3));
        assertTrue(comparator.compare(small, big) < 0);
        assertTrue(comparator.compare(big, small) > 0);
        assertEquals(0, comparator.compare(big, sameAsBig));
    }
}
