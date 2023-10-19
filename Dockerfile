FROM amazoncorretto:17-alpine-jdk AS build
RUN apk update && apk add maven
WORKDIR /workspace
COPY . .
RUN mvn package -Dmaven.test.skip=true -Dcheckstyle.skip=true

FROM amazoncorretto:17-alpine-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
COPY --from=build /workspace/src/main/resources/application.yml application.yml
ENTRYPOINT ["java","-jar","app.jar", "--spring.config.location=application.yml"]