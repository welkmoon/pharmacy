package kubg.edu.ua.automation.pharmacy.exception;

public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object... params) {
        super(message, params);
    }
}
