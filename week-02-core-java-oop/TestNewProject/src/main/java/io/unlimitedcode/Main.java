package io.unlimitedcode;

public class Main {

    static void main() {
        Student student1 = new Student("John", 18);
        student1.printer();

        Student student2 = new Student("Janet", 17);
        student2.printer();

        /*
         * - Updating the static property 'school' to a new value, affecting all Student objects.
         * - Reprinting the properties to show the shared static field's updated value.
         */
        Student.school = "Clayton High School";
        student1.printer();
        student2.printer();
    }
}
