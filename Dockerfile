FROM maven:3.8.5-openjdk-17 AS build
COPY . .

# ENV SPRING_PROFILES_ACTIVE=test
# RUN mvn test

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/api-0.0.1-SNAPSHOT.jar api.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","api.jar"]