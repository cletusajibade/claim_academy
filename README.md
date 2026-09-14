# Week 1 Java project

Open this folder in IntelliJ as a Maven project. Select the academy-approved JDK 21. Open `src/main/java/Main.java` and run the `main` method using the IDE run control. Maven may need internet access on its first build.

## Command-line alternative

From this project folder, with the JDK on your PATH:

```text
javac -d out src/main/java/Main.java
java -cp out Main
```

With Maven installed and dependencies available:

```text
mvn compile
java -cp target/classes Main
```

For a different class, replace Main with its class name and compile that source file. Each file is an independent console demonstration. Code uses the conventional class and main method syntax compatible with Java 21.

## Your project

Name: [your name]
What the program does: [describe]
How I verified it: [describe output and one change]
AI assistance: [none, or describe and verify]

Use your own descriptive commit. Do not publish the Instructor solutions folder to the learner repository.
