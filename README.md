# Spring Security Jwt Auth Boilerplate
## About
  This boilerplate was made for help creating an application startup with Spring-Security + JWT. It was partially based on the article [REST Security with JWT using Java and Spring Security](https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java).
## Requirements
- Maven 3.x.x and Java 1.8.
## Usage
Start the application with the Spring Boot maven plugin `mvn spring-boot:run`. It will be running at [http://localhost:8080](http://localhost:8080).

There are two endpoints to consume the api:
A POST to `/auth` with the content body
```
{
    "username":"username",
    "password":"password"
}
```
username/password is a mock for testing.

A GET to `/auth/refresh` with a header variable: 
```
key: Auth-Token
value: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImNyZWF0ZWQiOjE1MTA1NDIyNTE3MDksImV4cCI6MTUxMDU0NTg1MX0.A41DdCVJ9bkojPI25ihlQORcMI32mQmXyOiNZgeV31nl6nXRepbyp8CxXW2yoMyhDYB2tmPdXdjvTv_WKsWPsw
```
## Docker
There is a Dockerfile for running the project:
- execute to create the project image
```
docker build -f Dockerfile -t <imageTitle> .
```
- execute to run the project image with a VOLUME
```
docker run -it -p 8080:8080 -v <rootProjectDirectory>:/myApp/spring-jwt-auth-boilerplate <imageTitle>
```
