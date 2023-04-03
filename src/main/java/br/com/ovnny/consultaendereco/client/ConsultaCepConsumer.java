package br.com.ovnny.consultaendereco.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class ConsultaCepConsumer {

    @Value("${viacep.api.base-url}")
    private String viaCepBaseUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public ConsultaCepConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object buscaLocalizacaoPorCepConsumer(String cep) {
        String path = String.format(viaCepBaseUrl, cep);
        var url = URI.create(path);
        return restTemplate.getForObject(url, String.class);
    }
}