FROM eclipse-temurin:17-jre

ADD build/libs/order-service.jar /order-service.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:opentelemetry-javaagent.jar \
                 -Dotel.traces.exporter=logging \
                 -Dotel.metrics.exporter=logging \
                 -Dotel.logs.exporter=logging \
                 -jar /product-service.jar