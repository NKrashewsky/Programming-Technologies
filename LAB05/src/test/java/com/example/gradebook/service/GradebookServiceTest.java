package com.example.gradebook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.gradebook.entity.Assessment;
import com.example.gradebook.entity.Credit;
import com.example.gradebook.entity.Exam;
import com.example.gradebook.entity.Gradebook;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradebookServiceTest {

    private static final double DELTA = 1e-9;

    private final GradebookService service = new GradebookService();
    private final Exam math = new Exam("Mathematics", 5);
    private final Exam physics = new Exam("Physics", 4);
    private final Credit philosophy = new Credit("Philosophy", true);
    private final Credit sport = new Credit("PE", false);
    private final Exam programming = new Exam("Programming", 5);
    private final Exam databases = new Exam("Databases", 3);
    private Gradebook book;
    private Gradebook.Session first;
    private Gradebook.Session second;

    @BeforeEach
    void setUp() {
        book = new Gradebook("123", "Ivan", "CS-1");
        first = book.addSession(1);
        first.addAssessment(math);
        first.addAssessment(physics);
        first.addAssessment(philosophy);
        first.addAssessment(sport);
        second = book.addSession(2);
        second.addAssessment(programming);
        second.addAssessment(databases);
    }

    @Test
    void averageGrade() {
        assertEquals(4.25, service.averageGrade(book), DELTA);
    }

    @Test
    void averageSessionGrade() {
        assertEquals(4.5, service.averageSessionGrade(first), DELTA);
        assertEquals(4.0, service.averageSessionGrade(second), DELTA);
    }

    @Test
    void failedAssessments() {
        assertEquals(List.of(sport), service.failedAssessments(book));
        assertTrue(service.hasDebts(book));
    }

    @Test
    void failedExamCountsAsDebt() {
        second.addAssessment(new Exam("Algebra", 2));
        assertEquals(2, service.failedAssessments(book).size());
    }

    @Test
    void highestExam() {
        assertEquals(5, service.highestExam(book).orElseThrow().getGrade());
    }

    @Test
    void sortExamsByGrade() {
        assertEquals(List.of(physics, math), service.sortExamsByGrade(first));
    }

    @Test
    void sortBySubject() {
        List<Assessment> expected = List.of(math, sport, philosophy, physics);
        assertEquals(expected, service.sortBySubject(first));
    }

    @Test
    void excellentStudent() {
        Gradebook excellent = new Gradebook("1", "Anna", "G");
        Gradebook.Session session = excellent.addSession(1);
        session.addAssessment(new Exam("A", 5));
        session.addAssessment(new Credit("B", true));
        assertTrue(service.isExcellent(excellent));

        session.addAssessment(new Credit("C", false));
        assertFalse(service.isExcellent(excellent));
        assertFalse(service.isExcellent(book));
    }

    @Test
    void notExcellentWithoutExamsOrWithGradeFour() {
        Gradebook onlyCredits = new Gradebook("1", "Anna", "G");
        onlyCredits.addSession(1).addAssessment(new Credit("B", true));
        assertFalse(service.isExcellent(onlyCredits));

        Gradebook withFour = new Gradebook("2", "Petr", "G");
        Gradebook.Session session = withFour.addSession(1);
        session.addAssessment(new Exam("A", 5));
        session.addAssessment(new Exam("B", 4));
        assertFalse(service.isExcellent(withFour));
    }

    @Test
    void emptyGradebook() {
        Gradebook empty = new Gradebook("1", "Anna", "G");
        assertEquals(0, service.averageGrade(empty), DELTA);
        assertEquals(0, service.averageSessionGrade(empty.addSession(1)), DELTA);
        assertFalse(service.hasDebts(empty));
        assertTrue(service.highestExam(empty).isEmpty());
    }

    @Test
    void nullRejected() {
        assertThrows(NullPointerException.class, () -> service.averageGrade(null));
        assertThrows(NullPointerException.class, () -> service.averageSessionGrade(null));
        assertThrows(NullPointerException.class, () -> service.sortExamsByGrade(null));
        assertThrows(NullPointerException.class, () -> service.sortBySubject(null));
    }
}
