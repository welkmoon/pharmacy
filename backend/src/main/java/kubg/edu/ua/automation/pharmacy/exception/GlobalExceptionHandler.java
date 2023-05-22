package kubg.edu.ua.automation.pharmacy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_RESPONSE = "Something went wrong";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception error) {
        log.error("Error occurred during request handling", error);
        return new ExceptionResponse(500, DEFAULT_ERROR_RESPONSE, Instant.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApiException.class)
    public ExceptionResponse handleException(ApiException error) {
        log.error("Error occurred during request handling", error);
        return new ExceptionResponse(500, DEFAULT_ERROR_RESPONSE, Instant.now());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponse handleNotFoundException(NotFoundException notFoundException) {
        log.error("Error occurred during request handling", notFoundException);
        return new ExceptionResponse(403, notFoundException.getMessage(), Instant.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse handleBadRequestException(BadRequestException badRequestException) {
        log.error("Error occurred during request handling", badRequestException);
        return new ExceptionResponse(403, badRequestException.getMessage(), Instant.now());
    }

}
