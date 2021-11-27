package JEP306;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScientificCalculatorTest {

    @Test
    public void whenMethodOfstrictfpClassInvoked_thenIdenticalResultOnAllPlatforms() {
        ScientificCalculator calculator = new ScientificCalculator();
        double result = calculator.sum(23e10, 98e17);
        assertEquals(result, 9.800000230000001E18);

        result = calculator.diff(Double.MAX_VALUE, 1.56);
        assertEquals(result, 1.7976931348623157E308);
    }

}
