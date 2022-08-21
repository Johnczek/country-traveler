FROM maven:3.8.5-openjdk-17 AS MAVEN_BUILD

COPY pom.xml /build/
COPY .env /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:17.0-jdk
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/country-traveler-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "country-traveler-0.0.1-SNAPSHOT.jar"]