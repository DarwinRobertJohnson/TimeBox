#!

cd src

javac -d ../class/  app.java

cd ../class

java -classpath ".:sqlite-jdbc-3.7.2.jar" app
