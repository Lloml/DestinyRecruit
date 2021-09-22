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

    public static ResponseEntity<ProjectResponseBody> ok(String message) {
        return new ResponseEntity<>(
                new ProjectResponseBody(HttpStatus.OK.value(), message),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.OK
        );
    }

    public static ResponseEntity<ProjectResponseBody> ok(String message, Object data) {
        return new ResponseEntity<>(
                new ProjectResponseBody(HttpStatus.OK.value(), message, data),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.OK
        );
    }

    public static ResponseEntity<ProjectResponseBody> badRequest(String message) {
        return new ResponseEntity<>(
                new ProjectResponseBody(HttpStatus.BAD_REQUEST.value(), message),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static ResponseEntity<ProjectResponseBody> notFound(String message) {
        return new ResponseEntity<>(new ProjectResponseBody(HttpStatus.BAD_REQUEST.value(), message),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<ProjectResponseBody> badRequest(List<FieldError> fieldErrorsList) {
        var messageBuilder = new StringBuilder();
        fieldErrorsList.forEach(fieldError -> {
            messageBuilder.append(fieldError.getField());
            messageBuilder.append(' ');
            messageBuilder.append(fieldError.getDefaultMessage());
            messageBuilder.append(". ");
        });
        return new ResponseEntity<>(
                new ProjectResponseBody(HttpStatus.BAD_REQUEST.value(), messageBuilder.toString()),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static ResponseEntity<ProjectResponseBody> conflict(String message) {
        return new ResponseEntity<>(
                new ProjectResponseBody(HttpStatus.CONFLICT.value(), message),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<ProjectResponseBody> forbidden(String message, String error) {
        return new ResponseEntity<>(
                new ProjectResponseBody(HttpStatus.FORBIDDEN.value(), message, error),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.FORBIDDEN);
    }
}
