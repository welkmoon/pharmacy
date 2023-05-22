package kubg.edu.ua.automation.pharmacy.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private String message;

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException(String message, Object... params) {
        for (Object param : params) {
            message = StringUtils.replaceOnce(message, "{}", String.valueOf(param));
        }
        this.message = message;
    }
}
