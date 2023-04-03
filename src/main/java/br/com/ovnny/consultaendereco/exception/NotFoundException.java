package br.com.ovnny.consultaendereco.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    String message;
    HttpStatus httpStatus;

    public NotFoundException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}