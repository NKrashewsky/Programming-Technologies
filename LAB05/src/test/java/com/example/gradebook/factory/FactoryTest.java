package com.example.gradebook.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.gradebook.entity.Credit;
import com.example.gradebook.entity.Exam;
import com.example.gradebook.entity.Gradebook;
import com.example.gradebook.exception.InvalidGradebookDataException;
import org.junit.jupiter.api.Test;

class FactoryTest {

    private final ExamCreator exams = new ExamCreator();
    private final CreditCreator credits = new CreditCreator();
    private final GradebookCreator books = new GradebookCreator();
    private final AssessmentCreatorProvider provider = new AssessmentCreatorProvider();

    private static String[] p(String... values) {
        return values;
    }

    @Test
    void createsExam() throws Exception {
        Exam exam = (Exam) exams.createAssessment(p("EXAM", "1", "Math", "5"));
        assertEquals("Math", exam.getSubject());
        assertEquals(5, exam.getGrade());
    }

    @Test
    void examRejectsInvalidData() {
        assertThrows(InvalidGradebookDataException.class,
                () -> exams.createAssessment(p("EXAM", "1", "Math")));
        assertThrows(InvalidGradebookDataException.class,
                () -> exams.createAssessment(p("EXAM", "1", "", "5")));
        assertThrows(InvalidGradebookDataException.class,
                () -> exams.createAssessment(p("EXAM", "1", "Math", "7")));
        assertThrows(InvalidGradebookDataException.class,
                () -> exams.createAssessment(p("EXAM", "1", "Math", "abc")));
    }

    @Test
    void createsCredit() throws Exception {
        assertTrue(((Credit) credits.createAssessment(p("CREDIT", "1", "PE", "PASSED"))).isPassed());
        assertTrue(((Credit) credits.createAssessment(p("CREDIT", "1", "PE", "passed"))).isPassed());
        assertFalse(((Credit) credits.createAssessment(p("CREDIT", "1", "PE", "FAILED"))).isPassed());
    }

    @Test
    void unknownCreditResultReplacedWithDefault() throws Exception {
        assertFalse(credits.createAssessment(p("CREDIT", "1", "PE", "MAYBE")).isPassed());
    }

    @Test
    void creditRejectsInvalidData() {
        assertThrows(InvalidGradebookDataException.class,
                () -> credits.createAssessment(p("CREDIT", "1", "PE")));
        assertThrows(InvalidGradebookDataException.class,
                () -> credits.createAssessment(p("CREDIT", "1", " ", "PASSED")));
    }

    @Test
    void createsGradebook() throws Exception {
        Gradebook book = books.createGradebook(p("BOOK", "123", "Ivan Petrov", "CS-101"));
        assertEquals("123", book.getNumber());
        assertEquals("Ivan Petrov", book.getStudentName());
        assertEquals("CS-101", book.getGroup());
    }

    @Test
    void missingGroupReplacedWithDefault() throws Exception {
        assertEquals("N/A", books.createGradebook(p("BOOK", "123", "Ivan")).getGroup());
        assertEquals("N/A", books.createGradebook(p("BOOK", "123", "Ivan", " ")).getGroup());
    }

    @Test
    void gradebookRejectsInvalidData() {
        assertThrows(InvalidGradebookDataException.class,
                () -> books.createGradebook(p("BOOK", "123")));
        assertThrows(InvalidGradebookDataException.class,
                () -> books.createGradebook(p("BOOK", "", "Ivan")));
        assertThrows(InvalidGradebookDataException.class,
                () -> books.createGradebook(p("BOOK", "123", " ")));
    }

    @Test
    void providerReturnsCreators() throws Exception {
        assertInstanceOf(ExamCreator.class, provider.getCreator("exam"));
        assertInstanceOf(CreditCreator.class, provider.getCreator(" CREDIT "));
    }

    @Test
    void providerRejectsUnknownType() {
        assertThrows(InvalidGradebookDataException.class, () -> provider.getCreator("LAB"));
        assertThrows(InvalidGradebookDataException.class, () -> provider.getCreator(null));
    }
}
