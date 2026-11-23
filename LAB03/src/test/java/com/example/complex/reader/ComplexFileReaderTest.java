package com.example.complex.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.complex.entity.Complex;
import com.example.complex.entity.Fraction;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ComplexFileReaderTest {

    private final ComplexFileReader reader = new ComplexFileReader();

    @TempDir
    Path dir;

    @Test
    void readsValidLinesAndIgnoresInvalid() throws IOException {
        Path file = dir.resolve("complex.txt");
        Files.write(file, List.of(
                "# comment",
                "",
                "FRACTION;1;2;3;4",
                "INTEGER;5;-2",
                "FRACTION;1;0;2;3",
                "FRACTION;a;2;1;1",
                "INTEGER;7",
                "POLAR;1;2"));

        List<Complex> numbers = reader.read(file);

        assertEquals(3, numbers.size());
        assertEquals(new Complex(new Fraction(1, 2), new Fraction(3, 4)), numbers.get(0));
        assertEquals(new Complex(Fraction.of(5), Fraction.of(-2)), numbers.get(1));
        assertEquals(new Complex(Fraction.of(1), new Fraction(2, 3)), numbers.get(2));
    }

    @Test
    void emptyFileGivesEmptyList() throws IOException {
        Path file = dir.resolve("empty.txt");
        Files.write(file, List.of());
        assertTrue(reader.read(file).isEmpty());
    }

    @Test
    void missingFileThrows() {
        assertThrows(IOException.class, () -> reader.read(dir.resolve("none.txt")));
    }
}
