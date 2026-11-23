package com.example.gradebook.comparator;

import com.example.gradebook.entity.Exam;
import java.util.Comparator;

/**
 * Сравнение экзаменов по оценке.
 */
public class ExamGradeComparator implements Comparator<Exam> {

    @Override
    public int compare(Exam first, Exam second) {
        return Integer.compare(first.getGrade(), second.getGrade());
    }
}
