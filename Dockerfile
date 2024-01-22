FROM openjdk:17
#COPY ./src/main/java/com/example/BankingApplication /tmp
VOLUME /tmp
ARG BankingApplication
EXPOSE 8090
WORKDIR /tmp

COPY out/artifacts/BankingApplication_jar/ BankingApplication.jar
#ADD target/BankApplication-1.0.0.jar BankApplication.jar
ENTRYPOINT ["java","-jar","/BankingApplication"]