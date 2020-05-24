FROM openjdk:14-alpine
COPY build/libs/fe-test-*-all.jar fe-test.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "fe-test.jar"]