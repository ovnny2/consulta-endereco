Feature: Consulta de Endereco

  Scenario: Consulta endereço com sucesso
    Given que o usuário informa o CEP "01001000"
    When o usuário faz uma requisição para a API "http://localhost:8080/v1/consulta-enderecos"
    Then a resposta da API deve retornar o status 200
    Then a resposta da API deve conter o CEP "01001-000"
    Then a resposta da API deve conter a rua "Praça da Sé"
    Then a resposta da API deve conter o complemento "lado ímpar"
    Then a resposta da API deve conter o bairro "Sé"
    Then a resposta da API deve conter a cidade "São Paulo"
    Then a resposta da API deve conter o estado "SP"
    Then a resposta da API deve conter o valor do frete "7.85"

  Scenario: Consulta cep inexistente
    Given que o usuário informa o CEP não existente "99999-999"
    When o usuário faz uma requisição para a API "http://localhost:8080/v1/consulta-enderecos"
    Then a resposta da API deve retornar o status notfound 404
    Then a resposta da API deve conter a mensagem "Cep não encontrado. Verifique os dados de entrada e tente novamente"