FROM openjdk:11

COPY module-boot-api/build/libs/module-boot-api-0.0.1-SNAPSHOT.jar blog-search-service.jar

ENTRYPOINT ["java", "-jar", "blog-search-service.jar"]
