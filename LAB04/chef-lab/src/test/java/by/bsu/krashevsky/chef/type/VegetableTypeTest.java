package by.bsu.krashevsky.chef.type;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VegetableTypeTest {

    @DataProvider
    public Object[][] validTypes() {
        return new Object[][]{
                {"ROOT", VegetableType.ROOT},
                {"leafy", VegetableType.LEAFY},
                {"  Fruit ", VegetableType.FRUIT}
        };
    }

    @Test(dataProvider = "validTypes")
    public void parseValid(String value, VegetableType expected) {
        assertEquals(VegetableType.parse(value), Optional.of(expected));
    }

    @Test
    public void parseInvalidAndNull() {
        assertTrue(VegetableType.parse("BULB").isEmpty());
        assertTrue(VegetableType.parse(null).isEmpty());
    }
}
