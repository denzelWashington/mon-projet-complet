FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /opt/app
COPY pom.xml ./
COPY user-service/pom.xml ./user-service/
COPY task-service/pom.xml ./task-service/
COPY api-gateway/pom.xml ./api-gateway/
RUN mvn dependency:resolve dependency:resolve-plugins -B -pl task-service
COPY task-service ./task-service
RUN mvn package -DskipTests -B -pl task-service

FROM eclipse-temurin:21-jre AS runner
WORKDIR /opt/app
COPY --from=builder /opt/app/task-service/target/*.jar taskservice.jar
CMD ["java", "-jar", "taskservice.jar"]