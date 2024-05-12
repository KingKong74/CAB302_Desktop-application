package com.example.main_sem_proj.model.database;
import java.util.regex.*;

public class Validation {

    public Boolean ValidateEmail(String Email) {
        if (Email == null){
            return false;
        }
        else {
            String Regex = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[\\w]+)([.]((?:[a-zA-Z0-9-]){1,3})){1,2}";
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(Email);
            return matcher.matches();
        }
    }
    public Boolean ValidatePassword(String Password){

        if (Password == ""){
            return false;
        }
        else {
            return true;
        }
    }
}