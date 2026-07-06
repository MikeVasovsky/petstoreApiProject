# Petstore API Tests

Автотесты API Swagger Petstore.

## Технологии

[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white)](https://gradle.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/Swagger_Petstore-85EA2D?style=flat&logo=swagger&logoColor=black)](https://petstore3.swagger.io/)

## Библиотеки

[![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?style=flat&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Rest Assured](https://img.shields.io/badge/Rest_Assured-4E5D6C?style=flat)](https://rest-assured.io/)
[![Allure](https://img.shields.io/badge/Allure-FF6B00?style=flat)](https://allurereport.org/)
[![AssertJ](https://img.shields.io/badge/AssertJ-FF6F00?style=flat)](https://assertj.github.io/doc/)
[![Owner](https://img.shields.io/badge/Owner-5C6BC0?style=flat)](https://owner.aeonbits.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1A1A1A?style=flat&logo=lombok&logoColor=white)](https://projectlombok.org/)
[![JavaFaker](https://img.shields.io/badge/JavaFaker-00897B?style=flat)](https://github.com/DiUS/java-faker)
[![JSON Schema](https://img.shields.io/badge/JSON_Schema_Validator-3949AB?style=flat&logo=json&logoColor=white)](https://json-schema.org/)

## Запуск

```bash
docker pull swaggerapi/petstore3:latest
docker run -d --name petstore3 -p 8080:8080 swaggerapi/petstore3:latest
```

```bash
chmod +x gradlew
./gradlew test
```

```bash
docker stop petstore3 && docker rm petstore3
```
