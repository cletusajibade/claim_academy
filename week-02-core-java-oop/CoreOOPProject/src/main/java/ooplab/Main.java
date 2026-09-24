package ooplab;

public class Main {
    static void main(String[] args) {
        CourseResult courseResult = new CourseResult("Ava", 68);
        IO.println(courseResult.hasPassed());

        CourseResult.passingScore = 65;

        IO.println(courseResult.hasPassed());
    }
}
