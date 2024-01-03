FROM eclipse-temurin:17-jre

ADD build/libs/product-service.jar /product-service.jar
ADD build/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:opentelemetry-javaagent.jar \
                 -jar /product-service.jar