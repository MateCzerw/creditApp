# creditApp

In order to run application, please start active-mq with default settings from docker.

Active-mq start command:
docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis

The application now uses the embedded H2 database and Hibernate -> plan to rework it for JDBC with external DB

Please start modules with the following sequence:
Credit
Customer 
Product

!!!!Application tested only by manual tests in Postman!!!!



