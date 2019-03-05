# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Dhileepan Kruschev <dhileepan.kruschev@cognizant.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8081 available to the world outside this container
EXPOSE 8081

# The application's jar file
ARG JAR_FILE=target/taskmanager-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} taskmanager.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/taskmanager.jar"]

