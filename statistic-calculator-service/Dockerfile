FROM openjdk:14-alpine
COPY ./target/statistic-calculator-service-0.0.1-SNAPSHOT.jar /usr/app
WORKDIR /usr/app
RUN sh -c 'touch statistic-calculator-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "statistic-calculator-service-0.0.1-SNAPSHOT.jar"]
