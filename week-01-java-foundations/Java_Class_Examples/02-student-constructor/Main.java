public class Main {
    public static void main(String[] args) {
        Student student = new Student("Maya");
        student.study(2);
        student.study(3);
        Student partner = new Student("Leo");
        partner.study(4);
        System.out.println(student.introduce());
        System.out.println(student.studyHours);
        System.out.println(partner.introduce());
        System.out.println(partner.studyHours);
    }
}
