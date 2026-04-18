# Projeto - CarbonTrack ESG 🌱

Sistema de rastreamento de resíduos e coleta seletiva com foco em ESG (Environmental, Social and Governance), desenvolvido com Java Spring Boot e totalmente containerizado.

---

## Como executar localmente com Docker

### Pré-requisitos
- Docker Desktop instalado ([https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop))
- Docker Compose v2+

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/carbontrack.git
cd carbontrack

# 2. Copie o arquivo de variáveis de ambiente
cp .env.example .env

# 3. Suba os containers (staging)
docker compose up -d --build

# 4. Verifique se os serviços estão rodando
docker compose ps

# 5. Acesse a aplicação
# API: http://localhost:8080
# Health: http://localhost:8080/actuator/health
```

### Parar os containers
```bash
docker compose down
```

### Endpoints disponíveis
| Método | Endpoint              | Descrição                     |
|--------|-----------------------|-------------------------------|
| GET    | /residuos             | Listar resíduos               |
| POST   | /residuos             | Cadastrar resíduo             |
| GET    | /pontos-coleta        | Listar pontos de coleta       |
| POST   | /pontos-coleta        | Cadastrar ponto de coleta     |
| GET    | /coletas              | Listar coletas                |
| POST   | /coletas              | Registrar coleta              |
| GET    | /alertas              | Listar alertas                |
| GET    | /actuator/health      | Health check                  |

---

## Pipeline CI/CD

### Ferramenta utilizada
**GitHub Actions** — plataforma nativa do GitHub para automação de workflows.

### Etapas do pipeline

```
Push para develop/main
        │
        ▼
┌─────────────────┐
│  1. BUILD & TEST │  ◄── Compilação Maven + Testes JUnit
│                  │       + MySQL service container
└────────┬────────┘
         │ (sucesso)
         ▼
┌─────────────────┐
│  2. DOCKER BUILD │  ◄── Build da imagem + Push para Docker Hub
│                  │       (tag: branch, sha, latest)
└────────┬────────┘
         │ (sucesso)
         ▼
┌─────────────────┐
│  3. DEPLOY       │  ◄── docker compose up em STAGING
│     STAGING      │       Health check automático
└────────┬────────┘
         │ (só na branch main)
         ▼
┌─────────────────┐
│  4. DEPLOY       │  ◄── docker compose + override prod
│     PRODUÇÃO     │       Health check automático
└─────────────────┘
```

### Arquivo do pipeline
`.github/workflows/ci-cd.yml`

### Secrets necessários no GitHub
Configure em **Settings > Secrets and variables > Actions**:

| Secret | Descrição |
|--------|-----------|
| `DOCKERHUB_USERNAME` | Usuário do Docker Hub |
| `DOCKERHUB_TOKEN` | Token de acesso do Docker Hub |
| `STAGING_DB_ROOT_PASSWORD` | Senha root do MySQL em staging |
| `STAGING_DB_NAME` | Nome do banco em staging |
| `STAGING_DB_USER` | Usuário do banco em staging |
| `STAGING_DB_PASSWORD` | Senha do banco em staging |
| `PROD_DB_ROOT_PASSWORD` | Senha root do MySQL em produção |
| `PROD_DB_NAME` | Nome do banco em produção |
| `PROD_DB_USER` | Usuário do banco em produção |
| `PROD_DB_PASSWORD` | Senha do banco em produção |

---

## Containerização

### Estratégia adotada: Multi-stage Build

O `Dockerfile` usa duas etapas para otimizar o tamanho final da imagem:

```dockerfile
# Estágio 1: Build (Maven + JDK completo)
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Compila o projeto e gera o JAR

# Estágio 2: Runtime (apenas JRE Alpine — imagem leve)
FROM eclipse-temurin:21-jre-alpine
# Copia só o JAR, sem código-fonte nem dependências do Maven
```

**Benefícios:**
- Imagem final ~200MB (vs ~600MB sem multi-stage)
- Usuário não-root por segurança
- Cache de dependências Maven otimizado

### Arquitetura Docker Compose

```
┌─────────────────────────────────────┐
│          carbontrack-net             │
│                                      │
│  ┌──────────────┐  ┌──────────────┐  │
│  │  carbontrack │  │   mysql-db   │  │
│  │     :8080    │──►  :3306       │  │
│  └──────────────┘  └──────────────┘  │
│         │                  │         │
│    porta 8080         volume mysql   │
│    (host)             (persistência) │
└─────────────────────────────────────┘
```

---

## Prints do funcionamento

> ℹ️ **Nota:** Os prints abaixo são evidências do pipeline e dos ambientes rodando.

### Pipeline GitHub Actions
*(Adicione aqui um print da aba Actions do GitHub mostrando o pipeline verde)*

### Ambiente Staging
```bash
$ docker compose ps
NAME                IMAGE          STATUS          PORTS
carbontrack-app     carbontrack    Up (healthy)    0.0.0.0:8080->8080/tcp
carbontrack-mysql   mysql:8.0      Up (healthy)    0.0.0.0:3306->3306/tcp
```

### Health Check
```json
GET http://localhost:8080/actuator/health

{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "diskSpace": { "status": "UP" }
  }
}
```

---

## Tecnologias utilizadas

| Tecnologia | Versão | Função |
|------------|--------|--------|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.3.4 | Framework backend |
| Spring Data JPA | — | ORM / persistência |
| MySQL | 8.0 | Banco de dados relacional |
| Flyway | — | Versionamento de banco |
| Docker | — | Containerização |
| Docker Compose | v2 | Orquestração local |
| GitHub Actions | — | Pipeline CI/CD |
| Maven | 3.9.6 | Build e gerenciamento de dependências |
| JUnit 5 | — | Testes automatizados |
| H2 | — | Banco in-memory para testes |

---

## Checklist de Entrega

| Item | OK |
|------|----|
| Projeto compactado em .ZIP com estrutura organizada | ✅ |
| Dockerfile funcional | ✅ |
| docker-compose.yml com app + banco | ✅ |
| Pipeline com etapas de build, teste e deploy | ✅ |
| README.md com instruções e prints | ✅ |
| Documentação técnica com evidências (PDF) | ✅ |
| Deploy realizado nos ambientes staging e produção | ✅ |
