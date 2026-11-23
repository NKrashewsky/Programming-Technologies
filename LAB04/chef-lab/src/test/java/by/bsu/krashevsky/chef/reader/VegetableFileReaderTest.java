package by.bsu.krashevsky.chef.reader;

import by.bsu.krashevsky.chef.entity.Vegetable;
import by.bsu.krashevsky.chef.exception.VegetableException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

public class VegetableFileReaderTest {

    private final VegetableFileReader reader = new VegetableFileReader();

    @Test
    public void readsOnlyCorrectLines() throws IOException, VegetableException {
        Path file = Files.createTempFile("veg", ".txt");
        Files.write(file, List.of(
                "# комментарий",
                "ROOT;Морковь;120;41;true",
                "LEAFY;Салат;50;15;2",
                "FRUIT;Помидор;150;18;abc",
                "BULB;Лук;50;40;1",
                "мусор"), StandardCharsets.UTF_8);
        List<Vegetable> result = reader.read(file);
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getName(), "Морковь");
        Files.delete(file);
    }

    @Test
    public void readsProjectDataFile() throws VegetableException {
        List<Vegetable> result = reader.read(Path.of("data/vegetables.txt"));
        assertNotNull(result);
        assertTrue(result.size() >= 7);
    }

    @Test
    public void missingFileThrows() {
        VegetableException e = expectThrows(VegetableException.class,
                () -> reader.read(Path.of("no/such/file.txt")));
        assertNotNull(e.getCause());
    }

    @Test
    public void exceptionWithoutCause() {
        assertEquals(new VegetableException("msg").getMessage(), "msg");
    }
}
