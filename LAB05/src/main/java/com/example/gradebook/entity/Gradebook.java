package com.example.gradebook.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Зачётная книжка студента. Информация о сессиях хранится во внутреннем классе {@link Session}.
 */
public class Gradebook {

    private final String number;
    private final String studentName;
    private final String group;
    private final List<Session> sessions = new ArrayList<>();

    public Gradebook(String number, String studentName, String group) {
        this.number = number;
        this.studentName = studentName;
        this.group = group;
    }

    public String getNumber() {
        return number;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getGroup() {
        return group;
    }

    /**
     * Создаёт новую сессию и добавляет её в книжку.
     *
     * @param semester номер семестра
     * @return созданная сессия
     */
    public Session addSession(int semester) {
        Session session = new Session(semester);
        sessions.add(session);
        return session;
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    @Override
    public String toString() {
        return "Gradebook{number='" + number + "', student='" + studentName
                + "', group='" + group + "', sessions=" + sessions.size() + '}';
    }

    /**
     * Сессия: экзамены и зачёты одного семестра.
     * Это внутренний (нестатический) класс: каждая сессия связана со своей зачётной книжкой.
     */
    public class Session {

        private final int semester;
        private final List<Assessment> assessments = new ArrayList<>();

        private Session(int semester) {
            this.semester = semester;
        }

        public int getSemester() {
            return semester;
        }

        public void addAssessment(Assessment assessment) {
            assessments.add(assessment);
        }

        public List<Assessment> getAssessments() {
            return Collections.unmodifiableList(assessments);
        }

        /** Имя владельца книжки: берётся из внешнего объекта. */
        public String getStudentName() {
            return studentName;
        }

        @Override
        public String toString() {
            return "Session{student='" + studentName + "', semester=" + semester
                    + ", assessments=" + assessments.size() + '}';
        }
    }
}
