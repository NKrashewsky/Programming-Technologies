package com.example.gradebook.entity;

/**
 * Зачёт: сдан или не сдан.
 */
public class Credit extends Assessment {

    private final boolean passed;

    public Credit(String subject, boolean passed) {
        super(subject);
        this.passed = passed;
    }

    @Override
    public boolean isPassed() {
        return passed;
    }

    @Override
    public String toString() {
        return "Credit{subject='" + getSubject() + "', passed=" + passed + '}';
    }
}
