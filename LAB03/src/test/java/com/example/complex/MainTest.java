package com.example.complex;

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
        Path file = dir.resolve("c.txt");
        Files.write(file, List.of("INTEGER;1;2", "FRACTION;1;2;1;3"));
        assertDoesNotThrow(() -> Main.main(new String[] {file.toString()}));
    }

    @Test
    void runsWithEmptyFile() throws Exception {
        Path file = dir.resolve("e.txt");
        Files.write(file, List.of());
        assertDoesNotThrow(() -> Main.main(new String[] {file.toString()}));
    }

    @Test
    void handlesOverflow() throws Exception {
        Path file = dir.resolve("o.txt");
        Files.write(file, List.of(
                "INTEGER;9223372036854775807;9223372036854775807",
                "INTEGER;9223372036854775807;9223372036854775807"));
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
