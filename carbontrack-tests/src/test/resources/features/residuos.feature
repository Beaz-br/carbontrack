#language: pt

Funcionalidade: Gerenciamento de Residuos
  Como gestor de coleta seletiva
  Quero gerenciar os tipos de residuos cadastrados
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
  Cenario: Cadastrar um novo residuo com sucesso
    Quando eu faco uma requisicao POST para "/residuos" com o corpo:
      """
      {
        "tipoResiduo": "Eletronico",
        "descricao": "Equipamentos eletronicos descartados"
      }
      """
    Entao o status code da resposta deve ser 201
    E a resposta deve conter o campo "id"
    E a resposta deve conter o campo "tipoResiduo" com valor "Eletronico"

  @negativo
  Cenario: Tentar cadastrar residuo sem tipo
    Quando eu faco uma requisicao POST para "/residuos" com o corpo:
      """
      {
        "descricao": "Sem tipo informado"
      }
      """
    Entao o status code da resposta deve ser 400

  @positivo
  Cenario: Buscar residuo por ID existente
    Dado que existe um residuo cadastrado com ID 1
    Quando eu faco uma requisicao GET para "/residuos/1"
    Entao o status code da resposta deve ser 200
    E a resposta deve conter o campo "id" com valor "1"

  @negativo
  Cenario: Buscar residuo com ID inexistente
    Quando eu faco uma requisicao GET para "/residuos/9999"
    Entao o status code da resposta deve ser 404
