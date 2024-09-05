package login.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public boolean checkEmail(String email) {
        String validEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(validEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
    public boolean checkPassword(String password) {
        String validPassword = "^(?=.*[A-Z])(?=.*[.,-_;])(?=\\S+$).{7,15}$";
        Pattern pattern = Pattern.compile(validPassword);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
