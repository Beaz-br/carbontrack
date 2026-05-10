#language: pt

Funcionalidade: Gerenciamento de Pontos de Coleta
  Como administrador do sistema ESG
  Quero consultar os pontos de coleta de residuos
  Para monitorar a capacidade e nivel de cada ponto

  Contexto:
    Dado que a API do CarbonTrack esta disponivel

  @positivo
  Cenario: Listar todos os pontos de coleta com sucesso
    Quando eu faco uma requisicao GET para "/pontos-coleta"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista
    E a resposta deve conter o campo "localizacao"

  @positivo
  Cenario: Listar pontos de coleta retorna capacidade maxima
    Quando eu faco uma requisicao GET para "/pontos-coleta"
    Entao o status code da resposta deve ser 200
    E a resposta deve conter o campo "capacidadeMaxima"

  @negativo
  Cenario: Endpoint de pontos de coleta nao aceita POST
    Quando eu faco uma requisicao POST para "/pontos-coleta" com o corpo:
      """
      {
        "localizacao": "Teste",
        "capacidadeMaxima": 100
      }
      """
    Entao o status code da resposta deve ser 405