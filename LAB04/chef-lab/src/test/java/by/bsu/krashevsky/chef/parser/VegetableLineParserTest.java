package by.bsu.krashevsky.chef.parser;

import by.bsu.krashevsky.chef.type.VegetableType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VegetableLineParserTest {

    private final VegetableLineParser parser = new VegetableLineParser();

    @Test
    public void parseValidLine() {
        Optional<VegetableData> result = parser.parse("ROOT;Морковь;120;41;true");
        assertTrue(result.isPresent());
        VegetableData data = result.get();
        assertEquals(data.type(), VegetableType.ROOT);
        assertEquals(data.name(), "Морковь");
        assertEquals(data.weight(), 120.0);
        assertEquals(data.caloriesPer100g(), 41.0);
        assertEquals(data.extra(), "true");
    }

    @Test
    public void parseLineWithoutExtra() {
        Optional<VegetableData> result = parser.parse("FRUIT;Огурец;130;15");
        assertTrue(result.isPresent());
        assertEquals(result.get().extra(), "");
    }

    @DataProvider
    public Object[][] invalidLines() {
        return new Object[][]{
                {null},
                {""},
                {"   "},
                {"# комментарий"},
                {"ROOT;Репа"},
                {"BULB;Лук;50;40;1"},
                {"FRUIT;;100;20;5"},
                {"FRUIT;Помидор123;100;20;5"},
                {"LEAFY;Руккола;abc;25;1"},
                {"LEAFY;Руккола;50;abc;1"},
                {"ROOT;Свёкла;-5;43;true"},
                {"ROOT;Свёкла;0;43;true"},
                {"ROOT;Свёкла;6000;43;true"},
                {"ROOT;Редис;60;5000;true"}
        };
    }

    @Test(dataProvider = "invalidLines")
    public void parseInvalidLine(String line) {
        assertTrue(parser.parse(line).isEmpty());
    }
}
