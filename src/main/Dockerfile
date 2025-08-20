
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR  /app
COPY  . .
RUN  mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-ubi9-minimal
RUN  unlink /etc/localtime && ln -s /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime
WORKDIR /run
COPY --from=build /app/target/*.jar app.jar
EXPOSE  8080
ENV JAVA_OPTS="-Xmx2048m -Xms256m"

CMD ["/bin/sh", "-c", "java $JAVA_OPTS -jar app.jar"]
