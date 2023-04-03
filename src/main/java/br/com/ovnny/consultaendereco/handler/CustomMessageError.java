package br.com.ovnny.consultaendereco.handler;

import java.util.List;

public class CustomMessageError {

    List<String> errors;

    public CustomMessageError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}