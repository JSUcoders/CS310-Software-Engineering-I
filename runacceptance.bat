cd acceptance
javac -cp .;..\build\classes\main VolumeCalculatorKeywords.java
java -cp .;..\build\classes\main;C:\Users\Grady\Software\robotframework-2.9.jar org.robotframework.RobotFramework VolumeCalculatorTests.txt
cd ..