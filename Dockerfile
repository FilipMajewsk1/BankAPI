# Użyj bazowego obrazu z JDK 18, który odpowiada wersji Javy zdefiniowanej w pliku pom.xml
FROM openjdk:18-jdk-oracle

# Kopiuj artefakt aplikacji (plik JAR) do obrazu Dockera
COPY target/bank-0.0.1-SNAPSHOT.jar app.jar

# Ustaw punkt wejścia, aby uruchomić aplikację Java
ENTRYPOINT ["java","-jar","/app.jar"]