package com.example.complex.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ComplexTest {

    private final Complex z1 = new Complex(new Fraction(1, 2), new Fraction(3, 4));
    private final Complex z2 = new Complex(new Fraction(-2, 3), new Fraction(1, 6));

    @Test
    void getters() {
        assertEquals(new Fraction(1, 2), z1.getReal());
        assertEquals(new Fraction(3, 4), z1.getImaginary());
    }

    @Test
    void add() {
        Complex expected = new Complex(new Fraction(-1, 6), new Fraction(11, 12));
        assertEquals(expected, z1.add(z2));
    }

    @Test
    void multiply() {
        Complex expected = new Complex(new Fraction(-11, 24), new Fraction(-5, 12));
        assertEquals(expected, z1.multiply(z2));
    }

    @Test
    void imaginaryUnitSquaredIsMinusOne() {
        Complex i = new Complex(Fraction.ZERO, Fraction.ONE);
        assertEquals(new Complex(Fraction.of(-1), Fraction.ZERO), i.multiply(i));
    }

    @Test
    void squaredModulus() {
        assertEquals(new Fraction(13, 16), z1.squaredModulus());
    }

    @Test
    void equalsAndHashCode() {
        Complex copy = new Complex(new Fraction(2, 4), new Fraction(3, 4));
        assertEquals(z1, z1);
        assertEquals(z1, copy);
        assertEquals(z1.hashCode(), copy.hashCode());
        assertNotEquals(z1, z2);
        assertNotEquals(z1, new Complex(new Fraction(1, 2), Fraction.ZERO));
        assertNotEquals(z1, null);
        assertNotEquals(z1, "text");
    }

    @Test
    void toStringFormat() {
        assertEquals("1/2 + 3/4i", z1.toString());
        assertEquals("5 - 2i", new Complex(Fraction.of(5), Fraction.of(-2)).toString());
    }
}
