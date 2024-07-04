Required database: SQLite

The code was created using IntelliJ, it is recommended to run the program using the IDE
However if not possible then please run Main.java

if would like to use a different database make sure that the url contains "jdbc:sqlite:(rest of url)"



It is possible to run the program step by step by running the classes in sequence:

1- TestDatabaseConnection.java: tests if a connection to the database was successful

2- CreateTables.java:  creates the required tables in the database.

3- ParseXML,java: parses the XML data and inserts it into the previously created tables.
