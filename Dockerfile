#FROM ibmjava:8-jre-alpine
FROM openjdk:8u151-jdk-alpine3.7
WORKDIR project

ADD build/libs/app.jar .

CMD ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-jar","app.jar"]