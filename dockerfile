FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD target/testfile1.jar testfile1.jar
ENTRYPOINT ["java","-jar","/testfile1.jar"]