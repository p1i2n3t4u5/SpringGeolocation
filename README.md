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

to start application with embedded tomcat
mvn spring-boot:run




