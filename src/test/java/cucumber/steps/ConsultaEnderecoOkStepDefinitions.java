package cucumber.steps;

import br.com.ovnny.consultaendereco.enums.FretePorMacroRegiao;
import br.com.ovnny.consultaendereco.exception.NotFoundException;
import br.com.ovnny.consultaendereco.model.frete.CepClientResponse;
import br.com.ovnny.consultaendereco.model.frete.CepRequest;
import br.com.ovnny.consultaendereco.model.frete.FreteResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
public class ConsultaEnderecoOkStepDefinitions {

    private static final String consultaEnderecoEndpoint = "http://localhost:8080/v1/consulta-enderecos";
    private static final String viaCepBaseUrl = "http://viacep.com.br/ws/%s/json";
    private String cep = "01001-000";
    private CepRequest cepRequest = new CepRequest(cep);


    ResponseEntity<FreteResponse> clientResponse = new RestTemplate().postForEntity(consultaEnderecoEndpoint, cepRequest, FreteResponse.class);
    RestTemplate restTemplate = new RestTemplate();

    @Then("que o usuário informa o CEP {string}")
    public void que_o_usuario_conter_o_cep(String cep) {
        cep = clientResponse.getBody().getCep();
    }


    @Then("o usuário faz uma requisição para a API {string}")
    public void o_usuario_faz_uma_requisicao_para_a_api(String consultaEnderecoEndpoint) {
        restTemplate.postForEntity(consultaEnderecoEndpoint, new CepRequest(cep), CepRequest.class);
    }

    @Then("a resposta da API deve retornar o status {int}")
    public void a_resposta_da_api_deve_retornar_o_status(Integer status) {
        status = clientResponse.getStatusCodeValue();

    }

    @Then("a resposta da API deve conter o CEP {string}")
    public void a_resposta_da_api_deve_conter_o_cep(String cep) {
        clientResponse.getBody().getCep();
    }

    @Then("a resposta da API deve conter a rua {string}")
    public void a_resposta_da_api_deve_conter_a_rua(String rua) {
        rua = clientResponse.getBody().getRua();
    }

    @Then("a resposta da API deve conter o complemento {string}")
    public void a_resposta_da_api_deve_conter_o_complemento(String complemento) {
        complemento = clientResponse.getBody().getComplemento();
    }

    @Then("a resposta da API deve conter o bairro {string}")
    public void a_resposta_da_api_deve_conter_o_bairro(String bairro) {
        clientResponse.getBody().getBairro();

    }

    @Then("a resposta da API deve conter a cidade {string}")
    public void a_resposta_da_api_deve_conter_a_cidade(String cidade) {
        clientResponse.getBody().getCidade();
    }

    @Then("a resposta da API deve conter o estado {string}")
    public void a_resposta_da_api_deve_conter_o_estado(String estado) {
        clientResponse.getBody().getEstado();
    }

    @Then("a resposta da API deve conter o valor do frete {string}")
    public void a_resposta_da_api_deve_conter_o_valor_do_frete(String frete) {
        var path = String.format(viaCepBaseUrl, cep);
        var url = URI.create(path);

        var responseClient = restTemplate.getForEntity(url, CepClientResponse.class);

        FretePorMacroRegiao.precoDoFrete(responseClient.getBody().getIbge());
    }

    @Given("que o usuário informa o CEP não existente {string}")
    public void que_o_usuário_informa_o_cep_não_existente(String cepInexistente) {
        var cep = "99999-999";

    }

    @Then("a resposta da API deve retornar o status notfound {int}")
    public void aRespostaDaAPIDeveRetornarOStatusNotfound(int status) {
        var exception = new NotFoundException("Cep não encontrado. Verifique os dados de entrada e tente novamente");

        exception.getHttpStatus().value();
    }

    @Then("a resposta da API deve conter a mensagem {string}")
    public void a_resposta_da_api_deve_conter_a_mensagem(String string) {
        var exception = new NotFoundException("Cep não encontrado. Verifique os dados de entrada e tente novamente");

        exception.getMessage();

        Assert.assertTrue(exception instanceof NotFoundException);
    }
}