#language: pt

Funcionalidade: Registro de Coletas e Alertas ESG
  Como operador de coleta seletiva
  Quero registrar coletas realizadas e consultar alertas
  Para manter o historico e garantir conformidade ambiental

  Contexto:
    Dado que a API do CarbonTrack esta disponivel

  @positivo
  Cenario: Listar todas as coletas realizadas
    Quando eu faco uma requisicao GET para "/coletas"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista

  @positivo
  Cenario: Registrar nova coleta com sucesso
    Quando eu faco uma requisicao POST para "/coletas" com o corpo:
      """
      {
        "dataColeta": "2026-04-18",
        "pesoColetado": 25.5,
        "status": "CONCLUIDA",
        "idPontoColeta": 1,
        "idResiduo": 1
      }
      """
    Entao o status code da resposta deve ser 201
    E a resposta deve conter o campo "id"
    E a resposta deve conter o campo "status" com valor "CONCLUIDA"

  @negativo
  Cenario: Registrar coleta sem data
    Quando eu faco uma requisicao POST para "/coletas" com o corpo:
      """
      {
        "pesoColetado": 10.0,
        "status": "CONCLUIDA"
      }
      """
    Entao o status code da resposta deve ser 400

  @positivo
  Cenario: Listar todos os alertas do sistema
    Quando eu faco uma requisicao GET para "/alertas"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista

  @positivo
  Cenario: Verificar health check da aplicacao
    Quando eu faco uma requisicao GET para "/actuator/health"
    Entao o status code da resposta deve ser 200
    E a resposta deve conter o campo "status" com valor "UP"
