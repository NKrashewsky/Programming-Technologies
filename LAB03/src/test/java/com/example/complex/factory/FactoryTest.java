package com.example.complex.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.complex.entity.Complex;
import com.example.complex.entity.Fraction;
import com.example.complex.exception.InvalidComplexDataException;
import org.junit.jupiter.api.Test;

class FactoryTest {

    private final FractionComplexCreator fraction = new FractionComplexCreator();
    private final IntegerComplexCreator integer = new IntegerComplexCreator();
    private final ComplexCreatorProvider provider = new ComplexCreatorProvider();

    private static String[] p(String... values) {
        return values;
    }

    @Test
    void createsFromFractions() throws Exception {
        Complex c = fraction.createComplex(p("FRACTION", "2", "4", "3", "-6"));
        assertEquals(new Complex(new Fraction(1, 2), new Fraction(-1, 2)), c);
    }

    @Test
    void zeroDenominatorReplacedWithDefault() throws Exception {
        Complex c = fraction.createComplex(p("FRACTION", "1", "0", "2", "0"));
        assertEquals(new Complex(Fraction.of(1), Fraction.of(2)), c);
    }

    @Test
    void fractionRejectsInvalidData() {
        assertThrows(InvalidComplexDataException.class,
                () -> fraction.createComplex(p("FRACTION", "1", "2", "3")));
        assertThrows(InvalidComplexDataException.class,
                () -> fraction.createComplex(p("FRACTION", "a", "2", "3", "4")));
        assertThrows(InvalidComplexDataException.class,
                () -> fraction.createComplex(p("FRACTION", "1", "2", "3", "x")));
    }

    @Test
    void createsFromIntegers() throws Exception {
        assertEquals(new Complex(Fraction.of(5), Fraction.of(-2)),
                integer.createComplex(p("INTEGER", "5", "-2")));
    }

    @Test
    void integerRejectsInvalidData() {
        assertThrows(InvalidComplexDataException.class,
                () -> integer.createComplex(p("INTEGER", "7")));
        assertThrows(InvalidComplexDataException.class,
                () -> integer.createComplex(p("INTEGER", "1", "x")));
    }

    @Test
    void providerReturnsCreators() throws Exception {
        assertInstanceOf(FractionComplexCreator.class, provider.getCreator("fraction"));
        assertInstanceOf(IntegerComplexCreator.class, provider.getCreator(" INTEGER "));
    }

    @Test
    void providerRejectsUnknownType() {
        assertThrows(InvalidComplexDataException.class, () -> provider.getCreator("POLAR"));
        assertThrows(InvalidComplexDataException.class, () -> provider.getCreator(null));
    }
}
