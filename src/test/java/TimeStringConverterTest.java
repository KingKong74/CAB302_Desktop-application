import com.example.main_sem_proj.model.TimeStringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeStringConverterTest {

    private TimeStringConverter converter;

    @BeforeEach
    public void setUp(){
         converter = new TimeStringConverter();
    }

    @Test
    public void testValidTimeString() {
        String validTimeString = "12:30";
        assertEquals(validTimeString, converter.fromString(validTimeString));
    }

    @Test
    public void testInvalidTimeString() {
        String invalidTimeString = "25:70";
        assertNull(converter.fromString(invalidTimeString));
    }
}
