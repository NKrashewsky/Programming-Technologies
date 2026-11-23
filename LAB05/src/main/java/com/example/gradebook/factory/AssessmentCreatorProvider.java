package com.example.gradebook.factory;

import com.example.gradebook.exception.InvalidGradebookDataException;
import java.util.Map;

/**
 * Выбирает Creator по типу записи.
 */
public class AssessmentCreatorProvider {

    private final Map<String, AssessmentCreator> creators = Map.of(
            "EXAM", new ExamCreator(),
            "CREDIT", new CreditCreator());

    public AssessmentCreator getCreator(String type) throws InvalidGradebookDataException {
        AssessmentCreator creator = type == null ? null : creators.get(type.trim().toUpperCase());
        if (creator == null) {
            throw new InvalidGradebookDataException("Unknown record type: " + type);
        }
        return creator;
    }
}
