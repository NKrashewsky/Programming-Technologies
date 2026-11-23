package com.example.gradebook.comparator;

import com.example.gradebook.entity.Assessment;
import java.util.Comparator;

/**
 * Сравнение записей по названию предмета.
 */
public class SubjectComparator implements Comparator<Assessment> {

    @Override
    public int compare(Assessment first, Assessment second) {
        return first.getSubject().compareToIgnoreCase(second.getSubject());
    }
}
