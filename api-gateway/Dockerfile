FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /opt/app
COPY pom.xml ./
COPY user-service/pom.xml ./user-service/
COPY task-service/pom.xml ./task-service/
COPY api-gateway/pom.xml ./api-gateway/
RUN mvn dependency:resolve dependency:resolve-plugins -B -pl api-gateway
COPY api-gateway ./api-gateway
RUN mvn package -DskipTests -B -pl api-gateway

FROM eclipse-temurin:21-jre AS runner
WORKDIR /opt/app
COPY --from=builder /opt/app/api-gateway/target/*.jar apigateway.jar
CMD ["java", "-jar", "apigateway.jar"]