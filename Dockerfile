FROM openjdk:8-alpine
ADD cedcufps.jar cedcufps.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cedcufps.jar","/bin/bash"]
