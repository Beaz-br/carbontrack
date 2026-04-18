# =============================================
# Estágio 1: Build
# =============================================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro (cache de dependências)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do código-fonte
COPY src ./src

# Build sem testes (serão executados no pipeline CI/CD)
RUN mvn clean package -DskipTests

# =============================================
# Estágio 2: Runtime
# =============================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Usuário não-root por segurança
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copia o JAR do estágio de build
COPY --from=build /app/target/carbontrack-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
