package com.example.gradebook.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.gradebook.entity.Gradebook;
import com.example.gradebook.exception.InvalidGradebookDataException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GradebookFileReaderTest {

    private final GradebookFileReader reader = new GradebookFileReader();

    @TempDir
    Path dir;

    private Path write(String name, String... lines) throws IOException {
        Path file = dir.resolve(name);
        Files.write(file, List.of(lines));
        return file;
    }

    @Test
    void readsValidLinesAndIgnoresInvalid() throws Exception {
        Path file = write("book.txt",
                "# comment",
                "",
                "EXAM;1;Early;5",
                "BOOK;123;Ivan Petrov;CS-101",
                "BOOK;999;Other;X",
                "EXAM;1;Math;5",
                "CREDIT;1;PE;FAILED",
                "EXAM;2;Physics;4",
                "EXAM;0;Bad;4",
                "EXAM;13;Bad;4",
                "EXAM;x;Bad;4",
                "EXAM;3;Chem;7",
                "LAB;1;X;5",
                "EXAM");

        Gradebook book = reader.read(file);

        assertEquals("123", book.getNumber());
        assertEquals("Ivan Petrov", book.getStudentName());
        assertEquals(2, book.getSessions().size());
        assertEquals(1, book.getSessions().get(0).getSemester());
        assertEquals(2, book.getSessions().get(0).getAssessments().size());
        assertEquals(2, book.getSessions().get(1).getSemester());
        assertEquals(1, book.getSessions().get(1).getAssessments().size());
    }

    @Test
    void failsWithoutBookLine() throws IOException {
        Path onlyExams = write("exams.txt", "EXAM;1;Math;5");
        Path empty = write("empty.txt");
        Path invalidBook = write("invalid.txt", "BOOK;123");
        assertThrows(InvalidGradebookDataException.class, () -> reader.read(onlyExams));
        assertThrows(InvalidGradebookDataException.class, () -> reader.read(empty));
        assertThrows(InvalidGradebookDataException.class, () -> reader.read(invalidBook));
    }

    @Test
    void missingFileThrows() {
        assertThrows(IOException.class, () -> reader.read(dir.resolve("none.txt")));
    }
}
