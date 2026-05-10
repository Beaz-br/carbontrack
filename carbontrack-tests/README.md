# CarbonTrack - Testes BDD com Cucumber

Testes automatizados do projeto CarbonTrack usando **BDD (Behavior Driven Development)** com **Gherkin**, **Cucumber** e **REST Assured**.

---

## Pré-requisitos

- Java 21
- Maven 3.9+
- Aplicação CarbonTrack rodando (localmente ou via Docker)

---

## Como executar localmente

### 1. Suba a aplicação primeiro

```bash
# Na pasta raiz do carbontrack
docker compose up -d
```

Aguarde a aplicação ficar disponível em `http://localhost:8080`

### 2. Execute os testes

```bash
# Na pasta carbontrack-tests
mvn test
```

### 3. Executar apontando para outra URL

```bash
mvn test -Dapi.url=http://localhost:8080
```

---

## Relatórios

Após a execução, os relatórios ficam em:

```
target/cucumber-reports/
├── report.html   ← Abra no navegador para ver o resultado visual
├── report.json   ← Para integração com outras ferramentas
└── report.xml    ← Para o GitHub Actions
```

---

## Cenários de teste

### residuos.feature
| Cenário | Tipo | Endpoint |
|---------|------|----------|
| Listar todos os resíduos | Positivo | GET /residuos |
| Cadastrar novo resíduo | Positivo | POST /residuos |
| Cadastrar resíduo sem tipo | Negativo | POST /residuos |
| Buscar resíduo por ID | Positivo | GET /residuos/{id} |
| Buscar resíduo ID inexistente | Negativo | GET /residuos/9999 |

### pontos_coleta.feature
| Cenário | Tipo | Endpoint |
|---------|------|----------|
| Listar pontos de coleta | Positivo | GET /pontos-coleta |
| Cadastrar ponto de coleta | Positivo | POST /pontos-coleta |
| Cadastrar sem localização | Negativo | POST /pontos-coleta |
| Buscar ponto por ID | Positivo | GET /pontos-coleta/{id} |
| Buscar ID inexistente | Negativo | GET /pontos-coleta/9999 |

### coletas_alertas.feature
| Cenário | Tipo | Endpoint |
|---------|------|----------|
| Listar coletas | Positivo | GET /coletas |
| Registrar nova coleta | Positivo | POST /coletas |
| Registrar coleta sem data | Negativo | POST /coletas |
| Listar alertas | Positivo | GET /alertas |
| Health check | Positivo | GET /actuator/health |

---

## Tecnologias utilizadas

| Tecnologia | Versão | Função |
|------------|--------|--------|
| Cucumber | 7.15.0 | Framework BDD |
| Gherkin | — | Linguagem de cenários |
| REST Assured | 5.4.0 | Testes de API |
| JUnit 5 | 5.10.2 | Engine de execução |
| JSON Schema Validator | 5.4.0 | Testes de contrato |
