package cn.lloml.destinyrecruit.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.List;

public class CustomResponse {

    static HttpHeaders getGeneralHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static <T> ResponseEntity<CustomResponseBody<T>> ok(String message) {
        return new ResponseEntity<>(
                new CustomResponseBody<>(HttpStatus.OK.value(), message, ""),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.OK
        );
    }

    public static <T> ResponseEntity<CustomResponseBody<T>> ok(String message, T data) {
        return new ResponseEntity<>(
                new CustomResponseBody<>(HttpStatus.OK.value(), message, "", data),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.OK
        );
    }

    public static <T> ResponseEntity<CustomResponseBody<T>> badRequest(String message) {
        return new ResponseEntity<>(
                new CustomResponseBody<>(HttpStatus.BAD_REQUEST.value(), message, "Bad Request"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static <T> ResponseEntity<CustomResponseBody<T>> badRequest(List<FieldError> fieldErrorsList) {
        var messageBuilder = new StringBuilder();
        fieldErrorsList.forEach(fieldError -> {
            messageBuilder.append(fieldError.getField());
            messageBuilder.append(' ');
            messageBuilder.append(fieldError.getDefaultMessage());
            messageBuilder.append(". ");
        });
        return new ResponseEntity<>(
                new CustomResponseBody<>(HttpStatus.BAD_REQUEST.value(),messageBuilder.toString(), "Bad Request"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static <T> ResponseEntity<CustomResponseBody<T>> conflict(String message) {
        return new ResponseEntity<>(
                new CustomResponseBody<>(HttpStatus.CONFLICT.value(), message, "Conflict"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.CONFLICT);
    }

    public static <T> ResponseEntity<CustomResponseBody<T>> forbidden(String message, String error) {
        return new ResponseEntity<>(
                new CustomResponseBody<>(HttpStatus.FORBIDDEN.value(), message, error),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.FORBIDDEN);
    }
}
