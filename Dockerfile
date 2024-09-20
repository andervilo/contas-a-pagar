FROM openjdk:17-jdk-alpine

WORKDIR "/app"

COPY . .

RUN apk add maven

WORKDIR "/app"

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/contasapagar-0.0.1-SNAPSHOT.jar"]