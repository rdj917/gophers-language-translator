# Gophers Language Translator

##Lombok
This application uses Lombok - Java annotation library which helps to reduce boilerplate code. In order to compile you need to enable Java
 annotation processor of your IDE.

##Run the project:
 
**./gradlew bootRun**

##Run the built jar:
 
**java -jar gophers-language-translator-[VERSION].jar**

##Port

The default port is 8080. To override the server port use:

**./gradlew bootRun -Dserver.port=????**

**java -jar -Dserver.port=???? gophers-language-translator-[VERSION].jar**

##H2
H2 in-memory database is configured. To access the console while application is running, open http://localhost:8080/h2-console/ in the browser.
Use the default credentials.