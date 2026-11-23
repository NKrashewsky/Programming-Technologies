package com.example.gradebook.factory;

import com.example.gradebook.entity.Assessment;
import com.example.gradebook.entity.Credit;
import com.example.gradebook.exception.InvalidGradebookDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создаёт зачёт: CREDIT;semester;subject;PASSED|FAILED.
 */
public class CreditCreator extends AssessmentCreator {

    private static final Logger LOGGER = LogManager.getLogger(CreditCreator.class);

    @Override
    public Assessment createAssessment(String[] params) throws InvalidGradebookDataException {
        checkLength(params);
        return new Credit(parseSubject(params[SUBJECT_INDEX]), parseResult(params[VALUE_INDEX]));
    }

    /**
     * Неизвестный результат заменяется значением по умолчанию (не сдан).
     */
    private boolean parseResult(String raw) {
        if ("PASSED".equalsIgnoreCase(raw)) {
            return true;
        }
        if (!"FAILED".equalsIgnoreCase(raw)) {
            LOGGER.warn("Unknown credit result '{}', default FAILED is used", raw);
        }
        return false;
    }
}
