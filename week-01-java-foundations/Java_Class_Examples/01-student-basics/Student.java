public class Student {
    String name;
    int studyHours;

    void study(int hours) {
        studyHours += hours;
    }

    String introduce() {
        return "Hi, I am " + name;
    }
}