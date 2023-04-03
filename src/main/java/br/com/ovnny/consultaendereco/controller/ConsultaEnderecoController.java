package br.com.ovnny.consultaendereco.controller;

import br.com.ovnny.consultaendereco.model.frete.CepRequest;
import br.com.ovnny.consultaendereco.model.frete.FreteResponse;
import br.com.ovnny.consultaendereco.service.ConsultaValorFreteViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1")
public class ConsultaEnderecoController {

    private final ConsultaValorFreteViaCepService service;

    public ConsultaEnderecoController(ConsultaValorFreteViaCepService service) {
        this.service = service;
    }

    @PostMapping("/consulta-enderecos")
    public ResponseEntity<FreteResponse> consultaEndereco(@RequestBody @Valid CepRequest cep) {
        return ResponseEntity.ok(service.buscaEndereco(cep.getCep()));
    }
}