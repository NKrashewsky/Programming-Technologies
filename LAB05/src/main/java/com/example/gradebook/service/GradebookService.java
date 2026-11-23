package com.example.gradebook.service;

import com.example.gradebook.comparator.ExamGradeComparator;
import com.example.gradebook.comparator.SubjectComparator;
import com.example.gradebook.entity.Assessment;
import com.example.gradebook.entity.Exam;
import com.example.gradebook.entity.Gradebook;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Бизнес-логика над зачётной книжкой.
 */
public class GradebookService {

    private static final int EXCELLENT_GRADE = 5;

    private final Comparator<Exam> examComparator = new ExamGradeComparator();
    private final Comparator<Assessment> subjectComparator = new SubjectComparator();

    /** Средний балл по всем экзаменам (0, если экзаменов нет). */
    public double averageGrade(Gradebook gradebook) {
        return average(exams(allAssessments(gradebook)));
    }

    /** Средний балл по экзаменам одной сессии (0, если экзаменов нет). */
    public double averageSessionGrade(Gradebook.Session session) {
        return average(exams(session(session).getAssessments()));
    }

    /** Все несданные экзамены и зачёты (академические задолженности). */
    public List<Assessment> failedAssessments(Gradebook gradebook) {
        return allAssessments(gradebook).stream().filter(a -> !a.isPassed()).toList();
    }

    public boolean hasDebts(Gradebook gradebook) {
        return !failedAssessments(gradebook).isEmpty();
    }

    /** Отличник: есть экзамены, все на 5, и нет несданных зачётов. */
    public boolean isExcellent(Gradebook gradebook) {
        List<Exam> exams = exams(allAssessments(gradebook));
        return !exams.isEmpty()
                && exams.stream().allMatch(e -> e.getGrade() == EXCELLENT_GRADE)
                && !hasDebts(gradebook);
    }

    /** Экзамен с наивысшей оценкой. */
    public Optional<Exam> highestExam(Gradebook gradebook) {
        return exams(allAssessments(gradebook)).stream().max(examComparator);
    }

    /** Экзамены сессии по возрастанию оценки. */
    public List<Exam> sortExamsByGrade(Gradebook.Session session) {
        List<Exam> sorted = new ArrayList<>(exams(session(session).getAssessments()));
        sorted.sort(examComparator);
        return sorted;
    }

    /** Экзамены и зачёты сессии, отсортированные по названию предмета. */
    public List<Assessment> sortBySubject(Gradebook.Session session) {
        List<Assessment> sorted = new ArrayList<>(session(session).getAssessments());
        sorted.sort(subjectComparator);
        return sorted;
    }

    private double average(List<Exam> exams) {
        return exams.stream().mapToInt(Exam::getGrade).average().orElse(0);
    }

    private List<Exam> exams(List<Assessment> assessments) {
        return assessments.stream().filter(Exam.class::isInstance).map(Exam.class::cast).toList();
    }

    private List<Assessment> allAssessments(Gradebook gradebook) {
        return Objects.requireNonNull(gradebook, "gradebook must not be null")
                .getSessions().stream()
                .flatMap(s -> s.getAssessments().stream())
                .toList();
    }

    private Gradebook.Session session(Gradebook.Session session) {
        return Objects.requireNonNull(session, "session must not be null");
    }
}
