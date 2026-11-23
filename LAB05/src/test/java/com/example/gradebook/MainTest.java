package com.example.gradebook;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MainTest {

    @TempDir
    Path dir;

    @Test
    void runsWithFile() throws Exception {
        Path file = dir.resolve("g.txt");
        Files.write(file, List.of("BOOK;1;Ivan;G", "EXAM;1;Math;5", "CREDIT;1;PE;PASSED"));
        assertDoesNotThrow(() -> Main.main(new String[] {file.toString()}));
    }

    @Test
    void handlesFileWithoutBook() throws Exception {
        Path file = dir.resolve("e.txt");
        Files.write(file, List.of("EXAM;1;Math;5"));
        assertDoesNotThrow(() -> Main.main(new String[] {file.toString()}));
    }

    @Test
    void handlesMissingFile() {
        assertDoesNotThrow(() -> Main.main(new String[] {dir.resolve("none.txt").toString()}));
    }

    @Test
    void usesDefaultFile() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }
}
