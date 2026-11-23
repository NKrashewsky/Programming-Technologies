package com.example.gradebook.entity;

/**
 * Результат по одному предмету (экзамен или зачёт).
 */
public abstract class Assessment {

    private final String subject;

    protected Assessment(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * Сдан ли предмет.
     *
     * @return true, если предмет сдан
     */
    public abstract boolean isPassed();
}
