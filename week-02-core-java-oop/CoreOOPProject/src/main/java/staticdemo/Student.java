package staticdemo;

public class Student {
    String name;
    int age;
    final int id;

    // This is a static field that is shared among all objects of the class
    // just one copy exists in memory.
    // if the copy changes, all objects get this change.
    String school = "Claim Academy";

    static final int MAX_STUDENTS = 100;
    static int counter;

    public Student(String name, int age) {
        if (counter >= MAX_STUDENTS) {
            throw new IllegalStateException("Student creation limit reached");
        }

        this.name = name;
        this.age = age;
        this.id = ++counter;
    }

    void printer() {
        IO.println("\nName: " + name);
        IO.println("Age: " + age);
        IO.println("School: " + school);
        IO.println("Counter: " + counter);
        IO.println("ID: "+id);
    }

    public int getId(){
        return id;
    }

    public static int getCounter(){
        return counter;
    }
}
