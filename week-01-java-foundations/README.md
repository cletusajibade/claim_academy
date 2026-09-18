# Week 01: Java Foundations

This folder contains Claim Academy's introductory Java exercises, classroom demonstrations, and practice labs. Start with console programs and variables, then work toward classes, constructors, and encapsulation using student, car, and bank account examples.

## Learning goals

By working through these examples, you will practice how to:

- Compile and run a Java program and read its console output.
- Declare variables, choose data types, and use arithmetic, casting, and string concatenation.
- Explain variable scope and trace conditional statements and loops.
- Create objects, access fields, and call methods with arguments and return values.
- Initialize objects with constructors and distinguish separate objects from shared references.
- Use private fields, getters, setters, and validation to control changes to an object's state.
- Recognize packages and basic inheritance relationships.

## Project guide

Each project is independent. Open and build one project at a time: several folders define classes named `Main`, `Student`, or `Car`.

| Folder | What to explore | Entry point |
| --- | --- | --- |
| [week01-starter](week01-starter/) | Console output, expressions, conditionals, loops, objects, and basic inheritance | `Main`, `control.ControlStructures`, `Animal`, `Toyota`, or `Hyundai` |
| [StudentProgressLab](StudentProgressLab/) | Student information, primitive types, strings, and variable scope | `Main` or `VariablesAndScope` |
| [StudentLab2](StudentLab2/) | A small score tracker demonstrating division and casting | `LabMain` |
| [ClassesDemo](ClassesDemo/) | Student objects, fields, methods, constructors, and packages | `Main` |
| [CarProject](CarProject/) | Object state, acceleration, stopping, and shared references | `Main` |
| [BankProject](BankProject/) | Encapsulation, balances in whole cents, deposits, and withdrawals | `Main` |
| [Java_Class_Examples](Java_Class_Examples/) | Numbered examples, a bank lab starter, and reference solutions | `Main` in each numbered folder |

The Maven projects keep source files in `src/main/java`. The numbered folders under `Java_Class_Examples` contain Java files directly and do not require Maven.

## Setup

Use a Java Development Kit (JDK), a terminal or Java IDE such as IntelliJ IDEA, and Maven if you choose the Maven build commands.

Check the tools available in your terminal:

```sh
java -version
javac -version
mvn -version
```

### Java version settings

The projects currently have different compiler settings in their `pom.xml` files:

| Project | Configured Java version |
| --- | --- |
| `week01-starter` | 21 |
| `StudentProgressLab` | 25 |
| `StudentLab2`, `ClassesDemo`, `CarProject`, `BankProject` | 27 |

The [starter README](week01-starter/README.md) specifies JDK 21. The [numbered classroom examples](Java_Class_Examples/README.md) use Java 8-compatible syntax and no external dependencies.

Match your IDE and Maven JDK to the project you are running. If Maven reports `invalid target release: 27` on a JDK 21 or 25 installation, the project's compiler setting is higher than that JDK supports. Confirm the intended version with your instructor before adjusting project settings. Some projects use `IO.println` and shorter `main` declarations, so their source code also differs from the Java 21 starter.

## Run the examples

The commands below assume your terminal starts in `week-01-java-foundations`.

### In IntelliJ IDEA

1. Open an individual project folder, such as `week01-starter`.
2. Import its `pom.xml` as a Maven project and select an appropriate project JDK.
3. Open an entry-point class under `src/main/java` and run its `main` method.
4. Read the console output, change one value, and run it again to observe the result.

For a numbered classroom example, open that numbered folder and run its `Main` class.

### Starter project with Maven

```sh
cd week01-starter
mvn compile
java -cp target/classes Main
java -cp target/classes control.ControlStructures
```

Maven may need internet access on its first build. Run Maven from the selected project's folder; this week folder has no parent `pom.xml`.

### Starter project without Maven

Compile the top-level source files together with the control-flow example so that related classes are available:

```sh
cd week01-starter
javac -d out src/main/java/*.java src/main/java/control/*.java
java -cp out Main
java -cp out control.ControlStructures
```

### Numbered classroom examples

For example, run the first student demonstration:

```sh
cd Java_Class_Examples/01-student-basics
javac -d out *.java
java -cp out Main
```

Repeat from another numbered folder to explore that example. Compile each folder separately to avoid duplicate class names.

## Suggested practice order

1. Run `week01-starter/Main` and modify its printed messages and expressions.
2. Explore `StudentProgressLab` and `StudentLab2` to practice types, scope, and casting.
3. Run `control.ControlStructures` and predict which branches and loop iterations will execute.
4. Work through the student examples and `ClassesDemo` to connect classes, objects, methods, and constructors.
5. Explore `CarProject`: compare two separate cars with two variables that refer to the same car.
6. Explore `BankProject`: trace a deposit, a successful withdrawal, and a rejected withdrawal.
7. Attempt `Java_Class_Examples/06-lab-starter`, then compare your work with the bank lab solution in `05-bank-lab-solution`.

The bank lab starter intentionally contains scaffolding. Use the [classroom example guide](Java_Class_Examples/README.md) for the full teaching sequence and example assumptions.

## Check your understanding

- Can you explain why casting before division can change the result?
- Can you identify where a variable is in scope?
- Can you predict a program's output before running it?
- Can you explain the difference between a class, an object, and a reference variable?
- Can you show how a constructor initializes an object's fields?
- Can you explain why the bank account exposes deposit and withdrawal methods instead of a balance setter?
- Can you verify that a rejected withdrawal leaves the balance unchanged?

For each exercise, record what you changed, the output you expected, and the output you observed. These programs are learning exercises; the bank account model is not a production banking system.
