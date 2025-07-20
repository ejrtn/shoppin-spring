FROM eclipse-temurin:17-jdk
COPY img img
COPY *.jar shopping-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/shopping-spring.jar"]