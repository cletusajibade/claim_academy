package staticdemo;

public class Main {
    static void main(String[] args) {
        Student student1 = new Student("Reggie", 21);
        student1.printer();
        var value = Student.counter;

        Student student2 = new Student("Jules", 25);
        student2.printer();

        student1.name = "James";
        student1.printer();

        student2.printer();

        student2.school = "St Louis University";

        IO.println("After school name change");
        student1.printer();
        student2.printer();

        Student student3 = new Student("Eddy", 30);
        student3.school = "Clayton High School";
        student3.printer();

        student1.printer();
        student2.printer();
        student3.printer();

//        IO.println(student1);
//        IO.println(student2);

        IO.println("MAX_STUDENTS: " + Student.MAX_STUDENTS);

//        for (int i = 0; i < Student.MAX_STUDENTS; i++) {
//            new Student("Student-"+i,30);
//        }
        IO.println(Student.counter);
        Student student4 = new Student("Marion",20);
        student4.printer();
        IO.println(student4.getId());

        IO.println(Student.getCounter());

        Student student5 = null;
        student5 = new Student("Joe",50);
        student5.printer();
    }
}
