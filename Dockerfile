FROM openjdk:11
VOLUME /tmp
EXPOSE 9043
ADD ./target/mscustomer-0.0.1-SNAPSHOT.jar mscustomer.jar
ENTRYPOINT ["java","-jar","/mscustomer.jar"]