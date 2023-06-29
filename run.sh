#!

cd src

javac -d ../class/  notifier.java
javac -d ../class/  notification.java
cd ../class

java -classpath ".:sqlite-jdbc-3.7.2.jar" writer
#java -classpath ".:sqlite-jdbc-3.7.2.jar" viewer
java -classpath ".:sqlite-jdbc-3.7.2.jar" notifier
