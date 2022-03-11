FROM adoptopenjdk/openjdk11:alpine-jre
Run  mkdir -p /opt/app
WORKDIR /opt/app
COPY target/orderservice.jar /opt/app
ENTRYPOINT ["java","-jar","orderservice.jar"]