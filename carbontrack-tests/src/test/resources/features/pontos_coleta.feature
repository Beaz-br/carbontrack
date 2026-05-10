#language: pt

Funcionalidade: Gerenciamento de Pontos de Coleta
  Como administrador do sistema ESG
  Quero gerenciar os pontos de coleta de residuos
  Para monitorar a capacidade e nivel de cada ponto

  Contexto:
    Dado que a API do CarbonTrack esta disponivel

  @positivo
  Cenario: Listar todos os pontos de coleta
    Quando eu faco uma requisicao GET para "/pontos-coleta"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista
    E a resposta deve conter o campo "localizacao"

  @positivo
  Cenario: Cadastrar novo ponto de coleta com sucesso
    Quando eu faco uma requisicao POST para "/pontos-coleta" com o corpo:
      """
      {
        "localizacao": "Bairro Jardim Verde",
        "capacidadeMaxima": 150,
        "nivelAtual": 0
      }
      """
    Entao o status code da resposta deve ser 201
    E a resposta deve conter o campo "id"
    E a resposta deve conter o campo "localizacao" com valor "Bairro Jardim Verde"

  @negativo
  Cenario: Cadastrar ponto de coleta sem localizacao
    Quando eu faco uma requisicao POST para "/pontos-coleta" com o corpo:
      """
      {
        "capacidadeMaxima": 100
      }
      """
    Entao o status code da resposta deve ser 400

  @positivo
  Cenario: Buscar ponto de coleta por ID existente
    Dado que existe um ponto de coleta cadastrado com ID 1
    Quando eu faco uma requisicao GET para "/pontos-coleta/1"
    Entao o status code da resposta deve ser 200
    E a resposta deve conter o campo "capacidadeMaxima"

  @negativo
  Cenario: Buscar ponto de coleta com ID inexistente
    Quando eu faco uma requisicao GET para "/pontos-coleta/9999"
    Entao o status code da resposta deve ser 404
