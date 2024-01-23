# Użyj bazowego obrazu z JDK 18, który odpowiada wersji Javy zdefiniowanej w pliku pom.xml
FROM openjdk:18-jdk-oracle
RUN mkdir /app/
COPY ./ /app/
WORKDIR /app/
RUN mvn clean package -Pdocker_local

FROM openjdk:18-jdk-oracle
COPY --from=build /app/target/aplikacja.jar app.jar
RUN mkdir "db"

ENTRYPOINT ["java","-jar","/app.jar"]