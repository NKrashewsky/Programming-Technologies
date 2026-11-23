package com.example.gradebook.factory;

import com.example.gradebook.entity.Gradebook;
import com.example.gradebook.exception.InvalidGradebookDataException;
import com.example.gradebook.validator.GradebookValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создаёт зачётную книжку: BOOK;number;studentName[;group].
 */
public class GradebookCreator {

    private static final Logger LOGGER = LogManager.getLogger(GradebookCreator.class);
    private static final int REQUIRED_PARAMS = 3;
    private static final int GROUP_INDEX = 3;
    private static final String DEFAULT_GROUP = "N/A";

    private final GradebookValidator validator = new GradebookValidator();

    public Gradebook createGradebook(String[] params) throws InvalidGradebookDataException {
        if (params.length < REQUIRED_PARAMS) {
            throw new InvalidGradebookDataException("Expected at least " + REQUIRED_PARAMS
                    + " parameters but got " + params.length);
        }
        String number = params[1];
        String name = params[2];
        if (!validator.isValidText(number)) {
            throw new InvalidGradebookDataException("Gradebook number is empty");
        }
        if (!validator.isValidText(name)) {
            throw new InvalidGradebookDataException("Student name is empty");
        }
        return new Gradebook(number, name, parseGroup(params));
    }

    /**
     * Отсутствующая группа заменяется значением по умолчанию.
     */
    private String parseGroup(String[] params) {
        if (params.length > GROUP_INDEX && validator.isValidText(params[GROUP_INDEX])) {
            return params[GROUP_INDEX];
        }
        LOGGER.warn("Group is missing, default '{}' is used", DEFAULT_GROUP);
        return DEFAULT_GROUP;
    }
}
