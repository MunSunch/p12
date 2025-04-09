@echo off
javac -d com/munsun/target/classes com/munsun/src/*.java
jar cfe com/munsun/target/Task1.jar com.munsun.Main -C com/munsun/target/classes .
java -jar com/munsun/target/Task1.jar
pause