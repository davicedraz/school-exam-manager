FROM maven:3.6.0-jdk-8 as maven
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests
FROM openjdk:8-jre-alpine
WORKDIR /sems
COPY --from=maven target/school-exam-manager-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "./school-exam-manager-0.0.1-SNAPSHOT.jar"]