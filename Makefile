COMPILER = javac
SOURCE_FILE = Demo.java
SOURCE = ./src
TARGET = ./target
JVM = java

all:
	$(COMPILER) $(SOURCE)/$(SOURCE_FILE) -cp $(SOURCE) -d $(TARGET)

run:
	$(JVM) -cp $(TARGET) Demo