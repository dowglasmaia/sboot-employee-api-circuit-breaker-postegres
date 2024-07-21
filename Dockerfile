#Build
FROM maven:3.9.7-amazoncorretto-17 AS build
WORKDIR /api-docker
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean install

#Runtime
FROM amazoncorretto:17-alpine3.17
LABEL MAINTAINER="DOWGLAS MAIA"
ENV PORT=8082
WORKDIR /usr/src/app
RUN rm -f /etc/localtime && ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
COPY --from=build /api-docker/target/*.war /usr/src/app/ms-employee-api.war

ENTRYPOINT ["java" , "-Dfile.encoding=UTF-8", "-jar", "/usr/src/app/ms-employee-api.war", "--server.port=${PORT}"]

EXPOSE ${PORT}