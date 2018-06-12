FROM anapsix/alpine-java
LABEL maintainer="sravani.madireddy14@gmail.com"
COPY build/libs/springbootapp-pipeline-0.0.1-SNAPSHOT.jar /home/springbootapp-pipeline-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","-Dspring.data.mongodb.host=mongo-service","/home/springbootapp-pipeline-0.0.1-SNAPSHOT.jar"]
