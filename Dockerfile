#FROM ibmjava:8-jre-alpine
#FROM openjdk:8u151-jdk-alpine3.7
FROM openjdk:8u171-jdk-stretch
WORKDIR project

ADD build/libs/app.jar .

EXPOSE 9999
EXPOSE 9998

CMD ["java", \
    "-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap", \
    "-XX:MaxMetaspaceSize=32m", \
    "-Dcom.sun.management.jmxremote.port=9999", \
    "-Dcom.sun.management.jmxremote.authenticate=false", \
    "-Dcom.sun.management.jmxremote.ssl=false", \
    "-Dcom.sun.management.jmxremote.rmi.port=9998", \
    "-Djava.rmi.server.hostname=0.0.0.0", \
    "-XX:NativeMemoryTracking=summary", \
    "-jar","app.jar"]