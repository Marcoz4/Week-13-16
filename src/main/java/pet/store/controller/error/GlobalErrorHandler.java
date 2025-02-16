package pet.store.controller.error;

import java.util.Map;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import java.util.NoSuchElementException;

@RestControllerAdvice// Marks this class as a controller advice for exception handling
@Slf4j// Adds an SLF4J logger named "log" for logging
public class GlobalErrorHandler {
    @ExceptionHandler(NoSuchElementException.class)// Handles NoSuchElementException
    @ResponseStatus(HttpStatus.NOT_FOUND)// Sets the response status to 404 (Not Found)
    public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("Exception: {}", ex.toString());// Logs the error
        return Map.of("message", ex.toString());// Returns a map with the error message
    }
}
