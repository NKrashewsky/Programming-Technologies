package com.example.complex.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FractionTest {

    @Test
    void reducesToLowestTerms() {
        Fraction f = new Fraction(2, 4);
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());
    }

    @Test
    void movesSignToNumerator() {
        Fraction f = new Fraction(1, -2);
        assertEquals(-1, f.getNumerator());
        assertEquals(2, f.getDenominator());
        assertTrue(f.isNegative());
    }

    @Test
    void zeroIsNormalized() {
        Fraction f = new Fraction(0, 5);
        assertEquals(new Fraction(0, 1), f);
        assertTrue(f.isZero());
        assertFalse(f.isNegative());
    }

    @Test
    void zeroDenominatorRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Fraction(1, 0));
    }

    @Test
    void ofCreatesWholeNumber() {
        assertEquals(new Fraction(5, 1), Fraction.of(5));
    }

    @Test
    void add() {
        assertEquals(new Fraction(5, 6), new Fraction(1, 2).add(new Fraction(1, 3)));
    }

    @Test
    void subtract() {
        assertEquals(new Fraction(1, 6), new Fraction(1, 2).subtract(new Fraction(1, 3)));
    }

    @Test
    void multiply() {
        assertEquals(new Fraction(1, 2), new Fraction(2, 3).multiply(new Fraction(3, 4)));
    }

    @Test
    void negate() {
        assertEquals(new Fraction(-1, 2), new Fraction(1, 2).negate());
    }

    @Test
    void overflowDetected() {
        Fraction big = Fraction.of(Long.MAX_VALUE);
        assertThrows(ArithmeticException.class, () -> big.add(Fraction.ONE));
        assertThrows(ArithmeticException.class, () -> big.multiply(big));
    }

    @Test
    void equalsAndHashCode() {
        Fraction f = new Fraction(1, 2);
        assertEquals(f, f);
        assertEquals(f, new Fraction(2, 4));
        assertEquals(f.hashCode(), new Fraction(2, 4).hashCode());
        assertNotEquals(f, new Fraction(1, 3));
        assertNotEquals(f, new Fraction(2, 2));
        assertNotEquals(f, null);
        assertNotEquals(f, "1/2");
    }

    @Test
    void toStringFormat() {
        assertEquals("1/2", new Fraction(1, 2).toString());
        assertEquals("3", Fraction.of(3).toString());
        assertEquals("-3/4", new Fraction(3, -4).toString());
    }
}
