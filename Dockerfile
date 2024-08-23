FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /home/app

COPY . /home/app/ProjetoSi

RUN cd ProjetoSi && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:17-jre-alpine

WORKDIR /home/app

EXPOSE 8080

COPY --from=builder /home/app/ProjetoSi/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

