FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/shopping.jar shopping.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shopping.jar"]