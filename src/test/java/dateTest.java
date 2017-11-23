import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dateTest {

    @Test
    public void theDateTest() {

        System.out.println(LocalDate.now().minusDays(1));
    }
}
