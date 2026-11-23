package com.example.gradebook.reader;

import com.example.gradebook.entity.Assessment;
import com.example.gradebook.entity.Gradebook;
import com.example.gradebook.exception.InvalidGradebookDataException;
import com.example.gradebook.factory.AssessmentCreatorProvider;
import com.example.gradebook.factory.GradebookCreator;
import com.example.gradebook.validator.GradebookValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Читает зачётную книжку из текстового файла. Некорректные строки игнорируются.
 */
public class GradebookFileReader {

    private static final Logger LOGGER = LogManager.getLogger(GradebookFileReader.class);
    private static final String DELIMITER = ";";
    private static final String COMMENT = "#";
    private static final String BOOK = "BOOK";
    private static final int SEMESTER_INDEX = 1;

    private final AssessmentCreatorProvider provider = new AssessmentCreatorProvider();
    private final GradebookCreator bookCreator = new GradebookCreator();
    private final GradebookValidator validator = new GradebookValidator();

    /**
     * Читает файл.
     *
     * @param path путь к файлу
     * @return зачётная книжка со всеми корректными записями
     * @throws IOException                   если файл не удалось прочитать
     * @throws InvalidGradebookDataException если в файле нет корректной строки BOOK
     */
    public Gradebook read(Path path) throws IOException, InvalidGradebookDataException {
        List<String> lines = Files.readAllLines(path);
        LOGGER.info("Reading {} lines from {}", lines.size(), path);
        Gradebook gradebook = null;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty() || line.startsWith(COMMENT)) {
                continue;
            }
            String[] params = line.split(DELIMITER, -1);
            for (int j = 0; j < params.length; j++) {
                params[j] = params[j].trim();
            }
            try {
                if (BOOK.equalsIgnoreCase(params[0])) {
                    if (gradebook == null) {
                        gradebook = bookCreator.createGradebook(params);
                        LOGGER.debug("Created {}", gradebook);
                    } else {
                        LOGGER.warn("Line {} ignored: gradebook is already defined", i + 1);
                    }
                } else if (gradebook == null) {
                    LOGGER.error("Line {} ignored: gradebook is not defined yet ({})", i + 1, line);
                } else {
                    addAssessment(gradebook, params);
                }
            } catch (InvalidGradebookDataException e) {
                LOGGER.error("Line {} ignored: {} ({})", i + 1, e.getMessage(), line);
            }
        }
        if (gradebook == null) {
            throw new InvalidGradebookDataException("No valid BOOK line in " + path);
        }
        LOGGER.info("Loaded {}", gradebook);
        return gradebook;
    }

    private void addAssessment(Gradebook gradebook, String[] params)
            throws InvalidGradebookDataException {
        Assessment assessment = provider.getCreator(params[0]).createAssessment(params);
        int semester = parseSemester(params[SEMESTER_INDEX]);
        Gradebook.Session session = gradebook.getSessions().stream()
                .filter(s -> s.getSemester() == semester)
                .findFirst()
                .orElseGet(() -> gradebook.addSession(semester));
        session.addAssessment(assessment);
        LOGGER.debug("Added {} to semester {}", assessment, semester);
    }

    private int parseSemester(String raw) throws InvalidGradebookDataException {
        try {
            int semester = Integer.parseInt(raw);
            if (!validator.isValidSemester(semester)) {
                throw new InvalidGradebookDataException("Semester out of range: " + raw);
            }
            return semester;
        } catch (NumberFormatException e) {
            throw new InvalidGradebookDataException("Semester is not a number: " + raw);
        }
    }
}
