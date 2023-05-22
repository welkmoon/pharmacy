package kubg.edu.ua.automation.pharmacy.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailValidationUtil {

    private static final String EMAIL_REGEXP = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidEmail(String email) {
        return Pattern.compile(EMAIL_REGEXP)
                .matcher(email)
                .matches();
    }

}
