FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8081
ADD /target/Conditional_SpringBoot-0.0.1-SNAPSHOT_prod.jar prodapp.jar
ENTRYPOINT ["java","-jar","/prodapp.jar"]
