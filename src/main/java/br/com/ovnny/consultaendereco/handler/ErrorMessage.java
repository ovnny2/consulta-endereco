package br.com.ovnny.consultaendereco.handler;

public class ErrorMessage {
    String message;
    int httpStatus;

    public ErrorMessage(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }
}