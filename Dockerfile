FROM amazoncorretto:17

COPY target/*.jar app.jar

CMD ["java","-jar","/app.jar"]