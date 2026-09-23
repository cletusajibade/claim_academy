package io.unlimitedcode;

public class Student {
    String name;

    int age;

    /**
     * A static variable belongs to the class itself rather than to each object created from that class.
     * This 'school' variable is shared among all Student objects, making it a class-level property.
     * It holds the initial value "Claim Academy" but can be modified to reflect changes applicable to all instances.
     */
    static String school = "Claim Academy";

    /**
     * 'static' does not mean unchangeable. To declare a shared constant, use 'static final':
     */
    static final int MAX_STUDENTS = 100;

    /**
     * Another common use is counting how many objects have been created:
     */
    static int counter;

    public Student(String name, int age) {
        // Increment the counter for each new Student object created
        counter++;

        this.name = name;
        this.age = age;
    }

    void printer() {
        System.out.println("\nName: " + name);
        System.out.println("Age: " + age);
        System.out.println("School: " + school);
        System.out.println("Counter: " + counter);
    }
}
