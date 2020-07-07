# Gophers Language Translator

This application uses Lombok - Java annotation library which helps to reduce boilerplate code. In order to compile you need to enable Java
 annotation processor of your IDE.

Run with **./gradlew bootRun**

The default port is 8080. 
To override the server port type **-Dserver.port=XXX** in the command line.

H2 in-memory database is configured. To access the console while application is running, open http://localhost:8080/h2-console/ in the browser.
Use the default credentials.