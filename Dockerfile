FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-slim
COPY --from=build /target/UniversityStudentApp-0.0.1-SNAPSHOT.war UniversityStudentApp.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","UniversityStudentApp.war"]