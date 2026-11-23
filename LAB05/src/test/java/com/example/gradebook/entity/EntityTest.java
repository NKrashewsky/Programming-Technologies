package com.example.gradebook.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EntityTest {

    @Test
    void examPassedFromGradeThree() {
        assertTrue(new Exam("Math", 3).isPassed());
        assertTrue(new Exam("Math", 5).isPassed());
        assertFalse(new Exam("Math", 2).isPassed());
    }

    @Test
    void examGetters() {
        Exam exam = new Exam("Math", 4);
        assertEquals("Math", exam.getSubject());
        assertEquals(4, exam.getGrade());
        assertEquals("Exam{subject='Math', grade=4}", exam.toString());
    }

    @Test
    void credit() {
        Credit passed = new Credit("PE", true);
        Credit failed = new Credit("PE", false);
        assertTrue(passed.isPassed());
        assertFalse(failed.isPassed());
        assertEquals("PE", passed.getSubject());
        assertEquals("Credit{subject='PE', passed=true}", passed.toString());
    }

    @Test
    void gradebookGetters() {
        Gradebook book = new Gradebook("123", "Ivan", "CS-1");
        assertEquals("123", book.getNumber());
        assertEquals("Ivan", book.getStudentName());
        assertEquals("CS-1", book.getGroup());
        assertTrue(book.getSessions().isEmpty());
        assertTrue(book.toString().contains("Ivan"));
    }

    @Test
    void sessionIsStoredInGradebook() {
        Gradebook book = new Gradebook("123", "Ivan", "CS-1");
        Gradebook.Session session = book.addSession(2);
        assertEquals(2, session.getSemester());
        assertEquals(1, book.getSessions().size());
        assertEquals(session, book.getSessions().get(0));
    }

    @Test
    void sessionStoresExamsAndCredits() {
        Gradebook.Session session = new Gradebook("1", "Ivan", "G").addSession(1);
        Exam exam = new Exam("Math", 5);
        Credit credit = new Credit("PE", true);
        session.addAssessment(exam);
        session.addAssessment(credit);
        assertEquals(2, session.getAssessments().size());
        assertEquals(exam, session.getAssessments().get(0));
        assertThrows(UnsupportedOperationException.class,
                () -> session.getAssessments().add(new Exam("X", 4)));
    }

    @Test
    void sessionSeesOuterObject() {
        Gradebook.Session session = new Gradebook("1", "Ivan", "G").addSession(1);
        assertEquals("Ivan", session.getStudentName());
        assertTrue(session.toString().contains("Ivan"));
        assertTrue(session.toString().contains("semester=1"));
    }

    @Test
    void sessionsOfDifferentBooksAreIndependent() {
        Gradebook first = new Gradebook("1", "Ivan", "G");
        Gradebook second = new Gradebook("2", "Anna", "G");
        first.addSession(1).addAssessment(new Exam("Math", 5));
        second.addSession(1);
        assertEquals(1, first.getSessions().get(0).getAssessments().size());
        assertTrue(second.getSessions().get(0).getAssessments().isEmpty());
        assertEquals("Anna", second.getSessions().get(0).getStudentName());
    }

    @Test
    void sessionsListIsReadOnly() {
        Gradebook book = new Gradebook("1", "Ivan", "G");
        Gradebook.Session session = book.addSession(1);
        assertThrows(UnsupportedOperationException.class, () -> book.getSessions().add(session));
    }
}
