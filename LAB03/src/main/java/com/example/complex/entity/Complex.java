package com.example.complex.entity;

import java.util.Objects;

/**
 * Комплексное число, у которого действительная и мнимая части - рациональные дроби.
 */
public final class Complex {

    public static final Complex ZERO = new Complex(Fraction.ZERO, Fraction.ZERO);
    public static final Complex ONE = new Complex(Fraction.ONE, Fraction.ZERO);

    private final Fraction real;
    private final Fraction imaginary;

    public Complex(Fraction real, Fraction imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Fraction getReal() {
        return real;
    }

    public Fraction getImaginary() {
        return imaginary;
    }

    /** (a + bi) + (c + di) = (a + c) + (b + d)i. */
    public Complex add(Complex other) {
        return new Complex(real.add(other.real), imaginary.add(other.imaginary));
    }

    /** (a + bi)(c + di) = (ac - bd) + (ad + bc)i. */
    public Complex multiply(Complex other) {
        Fraction newReal = real.multiply(other.real).subtract(imaginary.multiply(other.imaginary));
        Fraction newImaginary = real.multiply(other.imaginary).add(imaginary.multiply(other.real));
        return new Complex(newReal, newImaginary);
    }

    /** Квадрат модуля a^2 + b^2 (остаётся рациональным, в отличие от самого модуля). */
    public Fraction squaredModulus() {
        return real.multiply(real).add(imaginary.multiply(imaginary));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Complex complex = (Complex) o;
        return real.equals(complex.real) && imaginary.equals(complex.imaginary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public String toString() {
        if (imaginary.isNegative()) {
            return real + " - " + imaginary.negate() + "i";
        }
        return real + " + " + imaginary + "i";
    }
}
