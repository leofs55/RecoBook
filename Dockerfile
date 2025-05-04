FROM eclipse-temurin:17
LABEL maintainer="leofigorelli@gmail.com"
WORKDIR /RecoBookApp
COPY target/RecoBook-0.0.1-SNAPSHOT.jar /RecoBookApp/RecoBook.jar
ENTRYPOINT ["java", "-jar", "RecoBook.jar"]