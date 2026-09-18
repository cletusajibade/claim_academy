package constructor;

public class Student2 {
    public String name;
    public int studyHours;
    public int age;
    public boolean isGraduated;
    public String address;

    // This is a constructor
    // It goes inside the class
    // it helps initialize the class properties/fields
    public Student2(String name) {
        this.name = name;
        studyHours = 0;
    }

    // method declaration
    // void means it does not return a value
    void study(int hours) {
        studyHours += hours;
    }

    String introduce() {
        return "Hi, my name is " + name;
    }
}

