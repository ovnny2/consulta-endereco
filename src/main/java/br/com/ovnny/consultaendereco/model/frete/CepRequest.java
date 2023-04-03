package br.com.ovnny.consultaendereco.model.frete;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Valid
public class CepRequest {

    @Pattern(
            regexp = "(^[0-9]{5})-?([0-9]{3}$)",
            message = "Cep Inválido. Verifique os dados de entrada e tente novamente. Ex: 12345-678"
    )

    @Size(min = 8, max = 9, message = "O cep deve ter no mínimo 8 números. O traço é opcional.")
    @NotBlank(message = "O cep é obrigatório")
    public String cep;

    public CepRequest(String cep) {
        this.cep = cep;
    }

    @Deprecated
    public CepRequest() {
    }

    public String getCep() {
        return cep;
    }
}