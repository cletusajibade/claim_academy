package ooplab;

public class CourseResult {
    String studentName;
    int score;
    static int passingScore = 70;

    public CourseResult(String studentName, int score){
        this.studentName = studentName;
        this.score = score;
    }

    boolean hasPassed(){
        return score >= passingScore;
    }
}
