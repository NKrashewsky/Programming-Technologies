package com.example.complex.service;

import com.example.complex.comparator.ComplexModulusComparator;
import com.example.complex.entity.Complex;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Операции над набором комплексных чисел.
 */
public class ComplexService {

    private final Comparator<Complex> modulusComparator = new ComplexModulusComparator();

    /** Сумма всех элементов (для пустого набора - 0). */
    public Complex sum(List<Complex> numbers) {
        return check(numbers).stream().reduce(Complex.ZERO, Complex::add);
    }

    /** Произведение всех элементов (для пустого набора - 1). */
    public Complex product(List<Complex> numbers) {
        return check(numbers).stream().reduce(Complex.ONE, Complex::multiply);
    }

    /** Число с наибольшим модулем. */
    public Optional<Complex> maxByModulus(List<Complex> numbers) {
        return check(numbers).stream().max(modulusComparator);
    }

    /** Копия набора, отсортированная по возрастанию модуля. */
    public List<Complex> sortByModulus(List<Complex> numbers) {
        List<Complex> sorted = new ArrayList<>(check(numbers));
        sorted.sort(modulusComparator);
        return sorted;
    }

    private List<Complex> check(List<Complex> numbers) {
        return Objects.requireNonNull(numbers, "numbers must not be null");
    }
}
