package com.example.gradebook;

import com.example.gradebook.entity.Gradebook;
import com.example.gradebook.exception.InvalidGradebookDataException;
import com.example.gradebook.reader.GradebookFileReader;
import com.example.gradebook.service.GradebookService;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Точка входа. Меню не реализовано по условию задачи.
 */
public final class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String DEFAULT_FILE = "data/gradebook.txt";

    private Main() {
    }

    public static void main(String[] args) {
        Path path = Path.of(args.length > 0 ? args[0] : DEFAULT_FILE);
        try {
            Gradebook gradebook = new GradebookFileReader().read(path);
            GradebookService service = new GradebookService();

            LOGGER.info("{}", gradebook);
            for (Gradebook.Session session : gradebook.getSessions()) {
                LOGGER.info("Semester {}: average {}, exams by grade {}, by subject {}",
                        session.getSemester(), service.averageSessionGrade(session),
                        service.sortExamsByGrade(session), service.sortBySubject(session));
            }
            LOGGER.info("Average grade: {}", service.averageGrade(gradebook));
            LOGGER.info("Debts: {}", service.failedAssessments(gradebook));
            LOGGER.info("Excellent student: {}", service.isExcellent(gradebook));
            service.highestExam(gradebook).ifPresent(e -> LOGGER.info("Highest exam: {}", e));
        } catch (IOException | InvalidGradebookDataException e) {
            LOGGER.error("Cannot load gradebook from {}", path, e);
        }
    }
}
