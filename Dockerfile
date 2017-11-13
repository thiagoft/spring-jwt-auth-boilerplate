FROM maven:latest

RUN mkdir /myApp
RUN mkdir /myApp/spring-jwt-auth-boilerplate

COPY . /myApp/spring-jwt-auth-boilerplate/

RUN mkdir /myApp/log
RUN mkdir /myApp/log/spring-jwt-auth-boilerplate/

#RUN apt-get update && apt-get install maven -y
RUN cd /myApp/spring-jwt-auth-boilerplate/ && mvn package

EXPOSE 8080

ENTRYPOINT java -jar /myApp/spring-jwt-auth-boilerplate/target/spring-jwt-auth-boilerplate.jar
