to generate liquibase file run command:
C:\project\oxygenworkspace\SpringGeolocation>mvn liquibase:generateChangeLog
 
 this will generate a file from existing database it will only generate the DML statements 


to generate migration script:
C:\project\oxygenworkspace\SpringGeolocation>mvn liquibase:updateSQL

migration script is for sharig your schema and all data inserted by liquibase



to initilize schema and data another way is put data.sql and schema.sql in classpath controller by below properties


spring.datasource.initialization-mode=embedded # Initialize the datasource with available DDL and DML scripts.
spring.datasource.data=classpath:data.sql  # Data (DML) script resource references.
#spring.datasource.schema= # Schema (DDL) script resource references.


to generate change set with existing data from database 
C:\project\oxygenworkspace\SpringGeolocation>mvn liquibase:generateChangeLog -Dliquibase.diffTypes="data"

to see dependency tree in case duplicate dependency present
mvn dependency:tree

to start application with embedded tomcat from codebase
-------------------------------------------------------
mvn spring-boot:run

start application from war file
--------------------------------
C:\project\oxygenworkspace\SpringGeolocation\target>java -jar SpringGeolocation.war

skip test while building 
-----------------------------
C:\project\oxygenworkspace\SpringGeolocation>mvn clean install -DskipTests=true



request mail format
-------------------------
{
"email":"niranjanpanigrahi2009@gmail.com",
"subject":"Things I wanna say to my Future self",
"body":"Dear Future me, <br><br> <b>Think Big And Don’t Listen To People Who Tell You It Can’t Be Done. Life’s Too Short To Think Small.</b> <br><br> Cheers, <br>Rajeev!",
"dateTime":"2018-10-25T12:04:00",
"timeZone":"Asia/Kolkata"
	
}



to enable gmail smtp access enable from below link
---------------------------------------------------

https://myaccount.google.com/u/1/lesssecureapps?pageId=none

to access swagger documentation
--------------------------------
http://localhost:8080/SpringGeolocation/v2/api-docs
http://localhost:8080/SpringGeolocation/swagger-ui.html

actuator end points with hal browser
--------------------------------------
http://localhost:8080/SpringGeolocation/actuator

http://localhost:8080/SpringGeolocation/browser/index.html#/SpringGeolocation  --> hal browser opens

http://localhost:8080/SpringGeolocation/browser/index.html#/SpringGeolocation/actuator  










