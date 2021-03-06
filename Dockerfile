FROM maven:3.6.0-jdk-8 AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM arm32v6/openjdk:8-jdk-alpine
COPY --from=build /usr/src/app/target/BotChatMans-0.1-jar-with-dependencies.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
