FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=dhileepan
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/dhileepan
ADD ./target/taskmanager-0.0.1-SNAPSHOT.jar /usr/src/taskmanager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","taskmanager-0.0.1-SNAPSHOT.jar"]