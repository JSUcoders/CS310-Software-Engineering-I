cd acceptance
javac -cp .;..\build\classes\main ArgsParserKeywords.java
java -cp .;..\build\classes\main;C:\RobotFramework\robotframework-3.0.jar org.robotframework.RobotFramework ArgsParserTests.txt
cd ..
pause