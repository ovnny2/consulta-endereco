package br.com.ovnny.consultaendereco.service;

import br.com.ovnny.consultaendereco.client.ConsultaCepConsumer;
import br.com.ovnny.consultaendereco.common.JsonToObject;
import br.com.ovnny.consultaendereco.enums.FretePorMacroRegiao;
import br.com.ovnny.consultaendereco.exception.NotFoundException;
import br.com.ovnny.consultaendereco.model.frete.CepClientResponse;
import br.com.ovnny.consultaendereco.model.frete.FreteResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ConsultaValorFreteViaCepService {


    static private String errorMessage = "{\n  \"erro\": true\n}";

    private final ConsultaCepConsumer consumer;

    public ConsultaValorFreteViaCepService(ConsultaCepConsumer consumer) {
        this.consumer = consumer;
    }

    public FreteResponse buscaEndereco(@PathVariable String cep) {
        Object clientResponse = consumer.buscaLocalizacaoPorCepConsumer(cep).toString();

        if (clientResponse.equals(errorMessage)) {
            throw new NotFoundException("Cep não encontrado. Verifique os dados de entrada e tente novamente");
        }

        JsonToObject.configSerialization();

        var freteResponseClient = JsonToObject.stringToEntity(clientResponse, CepClientResponse.class);

        String precoFrete = calculaValorFrete(freteResponseClient);

        return new FreteResponse(cep, precoFrete, freteResponseClient);
    }

    private String calculaValorFrete(CepClientResponse responseClient) {
        return FretePorMacroRegiao.precoDoFrete(responseClient.getIbge());
    }
}