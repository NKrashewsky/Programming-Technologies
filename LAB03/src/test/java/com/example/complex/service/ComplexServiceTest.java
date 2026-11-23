package com.example.complex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.complex.entity.Complex;
import com.example.complex.entity.Fraction;
import java.util.List;
import org.junit.jupiter.api.Test;

class ComplexServiceTest {

    private final ComplexService service = new ComplexService();
    private final Complex z1 = new Complex(new Fraction(1, 2), new Fraction(3, 4));
    private final Complex z2 = new Complex(new Fraction(-2, 3), new Fraction(1, 6));
    private final Complex z3 = new Complex(Fraction.of(5), Fraction.of(-2));
    private final List<Complex> numbers = List.of(z1, z2, z3);

    @Test
    void sum() {
        assertEquals(new Complex(new Fraction(29, 6), new Fraction(-13, 12)), service.sum(numbers));
    }

    @Test
    void product() {
        assertEquals(new Complex(new Fraction(-25, 8), new Fraction(-7, 6)), service.product(numbers));
    }

    @Test
    void emptyListUsesNeutralElements() {
        assertEquals(Complex.ZERO, service.sum(List.of()));
        assertEquals(Complex.ONE, service.product(List.of()));
        assertTrue(service.maxByModulus(List.of()).isEmpty());
    }

    @Test
    void maxByModulus() {
        assertEquals(z3, service.maxByModulus(numbers).orElseThrow());
    }

    @Test
    void sortByModulus() {
        assertEquals(List.of(z2, z1, z3), service.sortByModulus(numbers));
    }

    @Test
    void nullRejected() {
        assertThrows(NullPointerException.class, () -> service.sum(null));
    }
}
