public class Student {
    String name;
    int studyHours;

    public Student(String name) {
        this.name = name;
    }

    void study(int hours) {
        studyHours += hours;
    }

    String introduce() {
        return "Hi, I am " + name;
    }
}
