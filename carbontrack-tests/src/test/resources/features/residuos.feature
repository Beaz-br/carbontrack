#language: pt

Funcionalidade: Gerenciamento de Residuos
  Como gestor de coleta seletiva
  Quero consultar os tipos de residuos cadastrados
  Para garantir a correta destinacao e rastreabilidade dos materiais

  Contexto:
    Dado que a API do CarbonTrack esta disponivel

  @positivo
  Cenario: Listar todos os residuos cadastrados com sucesso
    Quando eu faco uma requisicao GET para "/residuos"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista
    E a resposta deve conter o campo "tipoResiduo"

  @positivo
  Cenario: Listar residuos retorna dados de plastico
    Quando eu faco uma requisicao GET para "/residuos"
    Entao o status code da resposta deve ser 200
    E a resposta deve conter o campo "descricao"

  @negativo
  Cenario: Endpoint de residuos nao aceita POST
    Quando eu faco uma requisicao POST para "/residuos" com o corpo:
      """
      {
        "tipoResiduo": "Teste",
        "descricao": "Teste"
      }
      """
    Entao o status code da resposta deve ser 405