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

    public static ResponseEntity<CustomResponseBody> ok(String message) {
        return new ResponseEntity<>(
                new CustomResponseBody(HttpStatus.OK.value(), message, ""),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.OK
        );
    }

    public static ResponseEntity<CustomResponseBody> ok(String message, Object data) {
        return new ResponseEntity<>(
                new CustomResponseBody(HttpStatus.OK.value(), message, "", data),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.OK
        );
    }

    public static ResponseEntity<CustomResponseBody> badRequest(String message) {
        return new ResponseEntity<>(
                new CustomResponseBody(HttpStatus.BAD_REQUEST.value(), message, "Bad Request"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static ResponseEntity<CustomResponseBody> notFound(String message){
        return  new ResponseEntity<>(new CustomResponseBody(HttpStatus.BAD_REQUEST.value(), message, "not found"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomResponseBody> badRequest(List<FieldError> fieldErrorsList) {
        var messageBuilder = new StringBuilder();
        fieldErrorsList.forEach(fieldError -> {
            messageBuilder.append(fieldError.getField());
            messageBuilder.append(' ');
            messageBuilder.append(fieldError.getDefaultMessage());
            messageBuilder.append(". ");
        });
        return new ResponseEntity<>(
                new CustomResponseBody(HttpStatus.BAD_REQUEST.value(), messageBuilder.toString(), "Bad Request"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static ResponseEntity<CustomResponseBody> conflict(String message) {
        return new ResponseEntity<>(
                new CustomResponseBody(HttpStatus.CONFLICT.value(), message, "Conflict"),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.CONFLICT);
    }

    public static ResponseEntity<CustomResponseBody> forbidden(String message, String error) {
        return new ResponseEntity<>(
                new CustomResponseBody(HttpStatus.FORBIDDEN.value(), message, error),
                CustomResponse.getGeneralHeaders(),
                HttpStatus.FORBIDDEN);
    }
}
