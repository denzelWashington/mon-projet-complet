FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /opt/app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY . ./
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre AS runner
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar apigateway.jar
CMD java -jar app.jar