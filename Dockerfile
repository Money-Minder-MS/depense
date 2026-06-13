FROM eclipse-temurin:17
COPY target/depense-0.0.1-SNAPSHOT.jar dep.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","dep.jar"]