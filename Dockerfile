FROM openjdk:8-alpine
ADD target/cedcufps.jar cedcufps.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cedcufps.jar","/bin/bash"]
