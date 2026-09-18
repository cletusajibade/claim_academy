import constructor.Student2;
import constructor.anotherpackage.AnotherClass;

public class Main {
    static void main() {
        Student student = new Student();
        // Dot notation
        student.name = "Leroy";
        student.age = 23;
        student.address ="St Louis";
        student.isGraduated = false;
        student.study(5); //method call
        student.study(3);
        IO.println(student.introduce());
        IO.println("I am studying for "+student.studyHours+" hours");

        IO.println();

        Student student2 = new Student();
        student2.name="James";
        student2.age = 35;
        student2.address ="St Charles";
        student2.isGraduated = false;
        student2.study(10);
        IO.println(student2.introduce());
        IO.println("I am studying for "+student2.studyHours+" hours");

        Student2 david = new Student2("David");
        IO.println(david.name);
        IO.println(david.age);
        IO.println(david.address);

        Student2 james = new Student2("James");
        IO.println(james.name);
    }
}
