package com.example.complex.entity;

import java.util.Objects;

/**
 * Рациональная дробь. Всегда хранится в несократимом виде, знаменатель положительный.
 */
public final class Fraction {

    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);

    private final long numerator;
    private final long denominator;

    /**
     * Создаёт дробь и приводит её к несократимому виду.
     *
     * @param numerator   числитель
     * @param denominator знаменатель (не ноль)
     * @throws IllegalArgumentException если знаменатель равен нулю
     */
    public Fraction(long numerator, long denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be zero");
        }
        long divisor = gcd(numerator, denominator);
        long sign = denominator < 0 ? -1 : 1;
        this.numerator = sign * numerator / divisor;
        this.denominator = sign * denominator / divisor;
    }

    public static Fraction of(long value) {
        return new Fraction(value, 1);
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public Fraction add(Fraction other) {
        return new Fraction(
                Math.addExact(Math.multiplyExact(numerator, other.denominator),
                        Math.multiplyExact(other.numerator, denominator)),
                Math.multiplyExact(denominator, other.denominator));
    }

    public Fraction subtract(Fraction other) {
        return add(other.negate());
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(Math.multiplyExact(numerator, other.numerator),
                Math.multiplyExact(denominator, other.denominator));
    }

    public Fraction negate() {
        return new Fraction(-numerator, denominator);
    }

    public boolean isZero() {
        return numerator == 0;
    }

    public boolean isNegative() {
        return numerator < 0;
    }

    private static long gcd(long first, long second) {
        long a = Math.abs(first);
        long b = Math.abs(second);
        while (b != 0) {
            long rest = a % b;
            a = b;
            b = rest;
        }
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return denominator == 1 ? String.valueOf(numerator) : numerator + "/" + denominator;
    }
}
