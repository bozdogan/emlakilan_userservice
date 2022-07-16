FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=*.jar
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]