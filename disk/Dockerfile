FROM maven:3.8.6-openjdk-18-slim as builder
# Build package in different container to get lighter final container
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests


# Packaging
FROM openjdk:18-jdk-slim-buster
COPY --from=builder /home/app/target/disk-0.0.1-SNAPSHOT.jar /usr/local/lib/disk.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/disk.jar"]
