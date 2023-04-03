package br.com.ovnny.consultaendereco.controller;

import br.com.ovnny.consultaendereco.enums.FretePorMacroRegiao;
import br.com.ovnny.consultaendereco.exception.NotFoundException;
import br.com.ovnny.consultaendereco.model.frete.CepClientResponse;
import br.com.ovnny.consultaendereco.model.frete.CepRequest;
import br.com.ovnny.consultaendereco.model.frete.FreteResponse;
import br.com.ovnny.consultaendereco.service.ConsultaValorFreteViaCepService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaEnderecoControllerTest {

    @Mock
    ConsultaValorFreteViaCepService service;

    @InjectMocks
    ConsultaEnderecoController controller;

    @Autowired
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should return a FreteResponse instance with HttpStatus OK")
    void consultaFrete() {

        CepRequest request = new CepRequest("30640-390");
        CepClientResponse clientResponse = new CepClientResponse(
                "30640-390",
                "Rua Apolo Oito",
                null,
                "Átila de Paiva (Barreiro)",
                "Belo Horizonte",
                "MG",
                "3106200",
                "",
                "31",
                "4123"
        );

        String precoFrete = FretePorMacroRegiao.precoDoFrete(clientResponse.getIbge());

        var expectedResponse = new FreteResponse(request.cep, precoFrete, clientResponse);

        when(service.buscaEndereco(request.getCep())).thenReturn(expectedResponse);

        var response = controller.consultaEndereco(request);

        assertEquals(expectedResponse, Objects.requireNonNull(response).getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return a MethodArgumentNotValidException instance and its violation when the input contains not allowed chars")
    void consultaFreteBadRequest() {
        CepRequest request = new CepRequest("00000*000");

        Set<ConstraintViolation<CepRequest>> violations = validator.validate(request);
        ConstraintViolation<CepRequest> violation = violations.iterator().next();


        assertEquals(1, violations.size());
        assertEquals("Cep Inválido. Verifique os dados de entrada e tente novamente. Ex: 12345-678", violation.getMessage());
        assertEquals("cep", violation.getPropertyPath().toString());
    }

    @Test
    @DisplayName("Should throw a BadRequestException if the input size is less than 8 and bigger than 9")
    void consultaFreteBadRequestSize() {
        CepRequest request = new CepRequest("3064390");

        var exception = new NotFoundException("Cep Inválido. Verifique os dados de entrada e tente novamente");

        when(service.buscaEndereco(request.getCep())).thenThrow(exception);

        var response = assertThrows(NotFoundException.class, () ->
                controller.consultaEndereco(request));

        assertEquals(exception.getMessage(), response.getMessage());
    }

    @Test
    @DisplayName("Should throw a MethodArgumentNotValidException when the request is null")
    void consultaFreteOrNull() {
        CepRequest request = new CepRequest(null);

        Set<ConstraintViolation<CepRequest>> violations = validator.validate(request);
        ConstraintViolation<CepRequest> violation = violations.iterator().next();


        assertEquals(1, violations.size());
        assertEquals("O cep é obrigatório", violation.getMessage());
        assertEquals("cep", violation.getPropertyPath().toString());
    }

    @Test
    @DisplayName("Should throw a MethodArgumentNotValidException when the request is null or blank")
    void consultaFreteBlank() {
        CepRequest request = new CepRequest("");

        Set<ConstraintViolation<CepRequest>> violations = validator.validate(request);
        ConstraintViolation<CepRequest> violation = violations.iterator().next();


        assertAll(
                () -> assertEquals(3, violations.size()),
                () -> assertEquals("cep", violation.getPropertyPath().toString())
        );


    }
}