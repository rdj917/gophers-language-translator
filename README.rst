===========================
Gophers Language Translator
===========================

Lombok
------

This application uses Lombok - Java annotation library which helps to reduce boilerplate code.

In order to compile you need to enable Java annotation processor of your IDE.

Run the project
---------------

::

    ./gradlew bootRun

Run the built jar
-----------------

::

    java -jar gophers-language-translator-[VERSION].jar

Port
----

The default port is 8080. To override the server port use:

::

    ./gradlew bootRun -Dserver.port=????

or

::

    java -jar -Dserver.port=???? gophers-language-translator-[VERSION].jar

H2
--

H2 in-memory database is configured.

To access the console while application is running, open `http://localhost:8080/h2-console <http://localhost:8080/h2-console>`_ in the browser.

Use the default credentials.

Swagger
-------

This application have Swagger documentation tool configured.

You could access it while application is running at `http://localhost:8080/swager-ui.html <http://localhost:8080/swager-ui.html>`_.

About
------

- Author: `Dimitar Dimitrov <https://www.linkedin.com/in/dimitar--dimitrov>`_
