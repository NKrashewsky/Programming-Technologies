package com.example.gradebook.entity;

/**
 * Экзамен: оценка по пятибалльной шкале (2-5).
 */
public class Exam extends Assessment {

    private static final int MIN_PASSING_GRADE = 3;

    private final int grade;

    public Exam(String subject, int grade) {
        super(subject);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public boolean isPassed() {
        return grade >= MIN_PASSING_GRADE;
    }

    @Override
    public String toString() {
        return "Exam{subject='" + getSubject() + "', grade=" + grade + '}';
    }
}
