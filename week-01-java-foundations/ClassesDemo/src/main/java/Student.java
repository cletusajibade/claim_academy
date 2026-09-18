public class Student {
    String name;
    int studyHours;
    int age;
    boolean isGraduated;
    String address;

    // method declaration
    // void means it does not return a value
    void study(int hours) {
        int updated = studyHours + hours;
        studyHours = updated;
    }

    String introduce() {
        return "Hi, my name is " + name;
    }
}
