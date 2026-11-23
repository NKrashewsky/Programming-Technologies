package com.example.gradebook.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.gradebook.entity.Credit;
import com.example.gradebook.entity.Exam;
import org.junit.jupiter.api.Test;

class ComparatorTest {

    @Test
    void examGradeComparator() {
        ExamGradeComparator comparator = new ExamGradeComparator();
        assertTrue(comparator.compare(new Exam("A", 3), new Exam("B", 5)) < 0);
        assertTrue(comparator.compare(new Exam("A", 5), new Exam("B", 3)) > 0);
        assertEquals(0, comparator.compare(new Exam("A", 4), new Exam("B", 4)));
    }

    @Test
    void subjectComparatorIgnoresCase() {
        SubjectComparator comparator = new SubjectComparator();
        assertTrue(comparator.compare(new Exam("algebra", 3), new Credit("Biology", true)) < 0);
        assertTrue(comparator.compare(new Credit("Chemistry", true), new Exam("Biology", 3)) > 0);
        assertEquals(0, comparator.compare(new Exam("Math", 3), new Credit("MATH", true)));
    }
}
