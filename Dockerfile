FROM maven:3.8.5-openjdk-11 AS MAVEN_BUILD

COPY pom.xml /build/
COPY .env /build/
COPY src /build/src/
COPY static /build/static

WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:17.0-jdk
COPY /static .
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/country-traveler-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "country-traveler-0.0.1-SNAPSHOT.jar"]