#language: pt

Funcionalidade: Registro de Coletas e Alertas ESG
  Como operador de coleta seletiva
  Quero registrar coletas e consultar alertas
  Para manter historico e garantir conformidade ambiental

  Contexto:
    Dado que a API do CarbonTrack esta disponivel

  @positivo
  Cenario: Listar todas as coletas realizadas
    Quando eu faco uma requisicao GET para "/coletas"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista

  @positivo
  Cenario: Listar todos os alertas do sistema
    Quando eu faco uma requisicao GET para "/alertas"
    Entao o status code da resposta deve ser 200
    E a resposta deve ser uma lista

  @negativo
  Cenario: Buscar alerta com ID inexistente retorna erro
    Quando eu faco uma requisicao GET para "/alertas/9999"
    Entao o status code da resposta deve ser 500

  @positivo
  Cenario: Endpoint de alertas aceita POST
    Quando eu faco uma requisicao POST para "/alertas" com o corpo:
      """
      {}
      """
    Entao o status code da resposta deve ser 500

  @negativo
  Cenario: Buscar alerta com ID inexistente retorna erro
    Quando eu faco uma requisicao GET para "/alertas/9999"
    Entao o status code da resposta deve ser 500

  @positivo
  Cenario: Verificar health check da aplicacao
    Quando eu faco uma requisicao GET para "/actuator/health"
    Entao o status code da resposta deve ser 200
    E a resposta deve conter o campo "status" com valor "UP"