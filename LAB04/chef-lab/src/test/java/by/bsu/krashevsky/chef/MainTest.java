package by.bsu.krashevsky.chef;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MainTest {

    @Test
    public void runWithDefaultFile() {
        Main.main(new String[0]);
    }

    @Test
    public void runWithCustomFile() throws IOException {
        Path file = Files.createTempFile("veg", ".txt");
        Files.write(file, List.of("ROOT;Морковь;120;41;true"), StandardCharsets.UTF_8);
        Main.main(new String[]{file.toString()});
        Files.delete(file);
    }

    @Test
    public void runWithMissingFileDoesNotThrow() {
        Main.main(new String[]{"no/such/file.txt"});
    }
}
