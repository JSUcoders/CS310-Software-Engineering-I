cd acceptance
javac -cp .;..\build\classes\main ArgsParserKeywords.java
java -cp .;..\build\classes\main;C:\Users\Grady\Software\robotframework-2.9.jar org.robotframework.RobotFramework ArgsParserTests.txt
cd ..