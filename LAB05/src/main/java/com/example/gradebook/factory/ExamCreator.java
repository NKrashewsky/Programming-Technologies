package com.example.gradebook.factory;

import com.example.gradebook.entity.Assessment;
import com.example.gradebook.entity.Exam;
import com.example.gradebook.exception.InvalidGradebookDataException;

/**
 * Создаёт экзамен: EXAM;semester;subject;grade.
 */
public class ExamCreator extends AssessmentCreator {

    @Override
    public Assessment createAssessment(String[] params) throws InvalidGradebookDataException {
        checkLength(params);
        return new Exam(parseSubject(params[SUBJECT_INDEX]), parseGrade(params[VALUE_INDEX]));
    }

    private int parseGrade(String raw) throws InvalidGradebookDataException {
        try {
            int grade = Integer.parseInt(raw);
            if (!validator.isValidGrade(grade)) {
                throw new InvalidGradebookDataException("Grade out of range: " + raw);
            }
            return grade;
        } catch (NumberFormatException e) {
            throw new InvalidGradebookDataException("Grade is not a number: " + raw);
        }
    }
}
