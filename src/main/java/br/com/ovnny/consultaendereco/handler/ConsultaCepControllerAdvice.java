package br.com.ovnny.consultaendereco.handler;

import br.com.ovnny.consultaendereco.exception.NotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ConsultaCepControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<CustomMessageError> handle(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new CustomMessageError(errors));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(description = "NotFound", responseCode = "404", content = @Content)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        var status = ex.getHttpStatus().value();
        var message = new ErrorMessage(ex.getMessage(), status);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}