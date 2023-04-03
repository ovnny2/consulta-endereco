package br.com.ovnny.consultaendereco.model.frete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public class CepClientResponse {

    @NotNull @Size(min = 8, max = 9)
    private String cep;

    @NotNull @Size(max = 64)
    private String logradouro;

    @Size(max = 128)
    private String complemento;

    @NotNull @Size(max = 32)
    private String bairro;

    @NotNull @Size(max = 32)
    private String localidade;

    @NotNull @Size(max = 2)
    private String uf;

    @NotNull @Size(max = 8)
    private String ibge;

    @JsonIgnore
    @Size(max = 16)
    public String gia;

    @JsonIgnore
    @NotNull @Size(max = 2)
    public String ddd;

    @JsonIgnore
    @NotNull @Size(max = 8)
    public String siafi;

    public CepClientResponse(
            String cep,
            String logradouro,
            String complemento,
            String bairro,
            String localidade,
            String uf,
            String ibge,
            String gia,
            String ddd,
            String siafi
    ) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    @Deprecated
    public CepClientResponse() {
    }

    public String getCep() { return cep; }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() { return bairro; }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getIbge() { return ibge; }
}