package arrays.arrays_lab;

public class ArraysLab {
    // {70, 80, 90}
    int total(int[] scores) {
        int sum = 0; // Accumulator
        for (int score : scores) {
            sum += score; //sum=sum+score
        }
        return sum;
    }

    void printSummary(int[] scores) {
        if (scores == null) {
            IO.println("Scores not available");
            return; // compiler returns from this point and stops execution.
        }

        if (scores.length == 0) {
            IO.println("No scores available");
            return;
        }

        // scores = {80, 105, 74}
        int min = scores[0]; //74
        int max = scores[1]; //74
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
            if (score > max) {
                max = score;
            }
        }
        // max = 105, min = 74
        // Loop 1, score = 70, min = 70, max = 70
        // Loop 2, score = 80, min = 70, max = 80
        // Loop 3, score = 90, min = 70, max = 90

        int sum = total(scores);
        double average = (double) sum / scores.length;

        IO.println("count= " + scores.length +
                ", total= " + sum +
                ", min=" + min +
                ", max=" + max +
                ", average=" + average);

    }

    static void main(String[] args) {
        int[] input = {80, 105, 74};
        //int[] input = new int[3]; //{0,0,0}
        ArraysLab arraysLab = new ArraysLab();
        arraysLab.printSummary(input);
    }
}
