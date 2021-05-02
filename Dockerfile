FROM ubuntu

RUN apt-get update

RUN apt-get install -y openjdk-8-jre

RUN apt-get install -y git

RUN mkdir /projects

WORKDIR /projects

RUN git clone https://github.com/jhodust/cedcufps.git

RUN cd cedcufps/

RUN git checkout current

RUN mvn clean

RUN mvn install

WORKDIR /target

ENTRYPOINT ["java","-jar","cedcufps/target/cedcufps-0.0.1-SNAPSHOT.jar","/bin/bash"]