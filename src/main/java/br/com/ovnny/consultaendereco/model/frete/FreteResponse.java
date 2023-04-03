package br.com.ovnny.consultaendereco.model.frete;

public class FreteResponse {
    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String precoFrete;

    public FreteResponse(String cep, String precoFrete, CepClientResponse clientResponse) {
        this.cep = cep;
        this.rua = clientResponse.getLogradouro();
        this.complemento = clientResponse.getComplemento();
        this.bairro = clientResponse.getBairro();
        this.cidade = clientResponse.getLocalidade();
        this.estado = clientResponse.getUf();
        this.precoFrete = precoFrete;
    }

    @Deprecated
    public FreteResponse() {
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() { return estado; }

    public String getPrecoFrete() {
        return precoFrete;
    }
}