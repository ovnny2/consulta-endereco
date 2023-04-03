package br.com.ovnny.consultaendereco.service;

import br.com.ovnny.consultaendereco.client.ConsultaCepConsumer;
import br.com.ovnny.consultaendereco.common.JsonToObject;
import br.com.ovnny.consultaendereco.enums.FretePorMacroRegiao;
import br.com.ovnny.consultaendereco.exception.NotFoundException;
import br.com.ovnny.consultaendereco.model.frete.CepClientResponse;
import br.com.ovnny.consultaendereco.model.frete.FreteResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaValorFreteViaCepServiceTest {


    @Mock
    ConsultaCepConsumer client;

    @InjectMocks
    ConsultaValorFreteViaCepService service;

    @Test
    @DisplayName("Should retrieve the correct shipping price")
    void buscaEndereco() {

        String cep = "30640-390";

        var responseClient = new CepClientResponse(
                "30640-390",
                "Rua Apolo Oito",
                "",
                "Átila de Paiva (Barreiro)",
                "Belo Horizonte",
                "MG",
                "3106200",
                "",
                "31",
                "4123"
        );


        String plainText = JsonToObject.entityToString(responseClient);

        when(client.buscaLocalizacaoPorCepConsumer(cep)).thenReturn(plainText);

        String expectedPrecoFrete = FretePorMacroRegiao.precoDoFrete(responseClient.getIbge());

        FreteResponse expectedResponse = new FreteResponse(cep, expectedPrecoFrete, responseClient);



        var result = service.buscaEndereco(cep);

        assertEquals(expectedResponse.getPrecoFrete(), result.getPrecoFrete());
    }

    @Test
    @DisplayName("Should throw NotFoundException if cep is not found")
    void buscaEnderecoNotFound() {

        String invalidCep = "99999-999";

        var exception = new NotFoundException("Não foi possível localizar informação para o cep informado");

        when(client.buscaLocalizacaoPorCepConsumer(invalidCep)).thenThrow(exception);

        var response = assertThrows(NotFoundException.class, () ->
                client.buscaLocalizacaoPorCepConsumer(invalidCep));

        assertEquals(exception.getMessage(), response.getMessage());
    }

    @Test
    @DisplayName("Should throw HttpClientErrorException.NotFound if cep is not found")
    void buscaEnderecoUnexpectedBehavior() {

        var exception = new RuntimeException("Ocorreu erro interno em nossos servidores. Tente novamente mais tarde");

        when(client.buscaLocalizacaoPorCepConsumer(anyString())).thenThrow(exception);

        var response = assertThrows(RuntimeException.class, () ->
                client.buscaLocalizacaoPorCepConsumer(anyString()));

        assertEquals(exception.getMessage(), response.getMessage());
    }
}