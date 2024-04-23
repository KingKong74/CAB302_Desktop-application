import com.example.main_sem_proj.model.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateEmailTest {
// the function should right now, return true if the email is valid and false if it is not
    private Validation validation;

    //Register Email Tests
    @BeforeEach
    void setup() { validation = new Validation();}

    @Test
    void validateEmail_withValidEmail(){
        String Test = "n11595710@qut.edu.au";
        Boolean result = validation.ValidateEmail(Test);
        assertEquals(true,result);
    }
    @Test
    void validateEmail_withNullEmail(){
        String Test = "";
        Boolean result = validation.ValidateEmail(Test);
        assertEquals(false,result);
    }
    @Test
    void validateEmail_withNoAtSymbolButDotCom(){
        String Test = "Charliekerr2004.com";
        Boolean result = validation.ValidateEmail(Test);
        assertEquals(false,result);
    }
    @Test
    void validateEmail_withAtSymbolButNoDotCom(){
        String Test = "Trainrunner47.com";
        Boolean result = validation.ValidateEmail(Test);
        assertEquals(false, result);
    }
    @Test
    void validateEmail_withNoAtSymbolAndNoDotCom(){
        String Test = "KingKong";
        Boolean result = validation.ValidateEmail(Test);
        assertEquals(false, result);
    }
    @Test
    void validateEmail_withInvalidCharacterAfterAt(){
        String Test = "Charliekerr@gm#il.com";
        Boolean result = validation.ValidateEmail(Test);
        assertEquals(false, result);
    }

    //Register Password Tests
    @Test
    void validatePassword_withValidPassword(){
        String Test = "Password123";
        Boolean result = validation.ValidatePassword(Test);
        assertEquals(true,result);
    }
    @Test
    void validatePassword_withNullPassword(){
        String Test = "";
        Boolean result = validation.ValidatePassword(Test);
        assertEquals(false,result);
    }

    //Other Register Tests
    @Test
    void testRegistrationButton(){

    }

}
