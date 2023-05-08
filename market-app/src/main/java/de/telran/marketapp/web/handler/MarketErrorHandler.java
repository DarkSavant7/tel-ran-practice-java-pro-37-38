package de.telran.marketapp.web.handler;

import de.telran.marketapp.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.net.HttpURLConnection;

@Slf4j
@ControllerAdvice
public class MarketErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e) {
        log.error("Something not found", e);
        var error = new ErrorDto(HttpURLConnection.HTTP_NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpURLConnection.HTTP_NOT_FOUND).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleUncaughtExceptions(Throwable e) {
        var message = "Something went wrong";
        log.error(message, e);
        var error = new ErrorDto(HttpURLConnection.HTTP_INTERNAL_ERROR, message);
        return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).body(error);
    }
}
