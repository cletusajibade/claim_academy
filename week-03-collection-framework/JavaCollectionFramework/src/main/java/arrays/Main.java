package arrays;

import java.util.Arrays;

public class Main {
    static int age;
    static boolean isStudent;
    char letter = 'A';
    static String name;

    static void main(String[] args) {
        IO.println("age= " + age);
        IO.println("isStudent= " + isStudent);
        IO.println("name= " + name);
        int total = 0;
        for (int i = 1; i <= 3; i++) {
            total += i; //total = total + i;
        }
        // Loop 1, i=1, total = 1,
        // Loop 2, i=2, total = 3,
        // Loop 3, i=3, total = 6,
        // Loop 4, i=4, total = 6,

        // Reference data types: String, List, Set, Any class you create
        // Any object created with the "new" keyword = reference type

        int[] numbers = new int[5];
        numbers[0] = 5;
        numbers[1] = 7;
        numbers[2] = 1;
        numbers[3] = 7;
        numbers[4] = 3; //n-1 or size-1
        //numbers[5] = 8; // throws an exception
        int[] numbers2 = {5, 7, 1, 7, 3};
        //ArrayList - dynamic, meaning it can grow or shrink.

        int[] scores;
        int[] scores2 = new int[3];
        int[] scoresKnown = {70, 83, 90};
        scores = new int[]{83, 70, 90, 66};

        IO.println(scores2[0]);
        IO.println(scores2[1]);
        IO.println(scores2[2]);

        double[] speeds = new double[5];
        IO.println(speeds[0]);
        IO.println(speeds[1]);
        IO.println(speeds[2]);

        String[] names = new String[3];
        IO.println(names[0]);
        IO.println(Arrays.toString(scores));
//        Arrays.sort(scores);
        IO.println(Arrays.toString(scores));

//        IO.println(scores[1]);
        //IO.println(scores[-1]);

//        for (int i = 0; i < scores.length; i++) {
//            IO.println("Scores[" + (i + 1) + "]: " + scores[i]);
//            //The array prints out as below:
//            //Score[1]: 65
//            //Score[2]: 70
//        }

        // You use this when you are not interested in
        // the index of the array
//        for (int score : scores) {
//            IO.println(score);
//        }

        // Reverse
        //Input = {83, 70, 90, 65}
        //Output = {65, 90, 70, 83}
        for (int i = scores.length - 1; i >= 0; i--) {
            IO.println(scores[i]);
        }

        // scores = {83, 70, 90, 66}
        int sum = 0; //accumulator
        //enhanced for-loop; for-each
        for (int score : scores) {
            sum += score;// sum = sum + score
        }
        IO.println(sum);
        double average = (double) sum / scores.length;
        double average2 = sum / scores.length;

        IO.println(average);
        IO.println(average2);

        int[] numbers3 = {20, 30, 40, 50};
        IO.println(Arrays.toString(numbers3));
        IO.println(numbers3);

        int[] numbers4 = {30, 20, 40, 50};
        boolean result = Arrays.equals(numbers3, numbers4);
        IO.println(result);

        int[][] matrix = new int[2][2];//{{1,2},{3,4}}

        int[] a = {10, 12, 7, 14, 20};
        int[] b;
        b = a;
        IO.println("Array a= " + Arrays.toString(a));
        IO.println("Array b= " + Arrays.toString(b));

        int[] c = a.clone();
        IO.println("Array c= " + Arrays.toString(c));

        IO.println(a);
        IO.println(b);
        IO.println(c);

        int[] d = Arrays.copyOf(a, 10);
        IO.println("Array d= " + Arrays.toString(d));

        int[] e = Arrays.copyOfRange(a, 2, 10);
        IO.println("Array e= " + Arrays.toString(e));

        System.arraycopy(a, 1, e, 2, 2);
        // Copies in-place.
        IO.println("Array a= " + Arrays.toString(a));
        IO.println("Updated e= " + Arrays.toString(e));

        int[] f = {10, 12, 7, 14, 20};
        //int value = f[3];
        for (int i = 0; i < f.length; i++) {
            //--- % - modulus
            if (f[i] % 2 == 0) {
                IO.println(f[i] + " is even");
            } else {
                IO.println(f[i] + " is odd");
            }
        }

        //Search for 7;
        int niddle = 10;
        int[] f2 = {10, 12, 7, 14, 20};
        search(f2, niddle);
    }

    static void search(int[] f, int needle) {
        boolean found = false;

        // Linear search - O(n), O(log n)
        for (int i = 0; i < f.length; i++) {
            if (f[i] == needle) {
                found = true;
                break;
            }
        }

        if (found) IO.println("Found " + needle);
        else IO.println(needle + " not found");

        String[] names = {"James", "Reggie", "Jules"};
        IO.println(Arrays.toString(names));
        names[2]="Cletus";

        String[] updatedNames = names;
        String[] updatedNames2 = Arrays.copyOf(names,3);

        IO.println("names= "+Arrays.toString(names));
//        IO.println("updatedNames= "+Arrays.toString(updatedNames));
        IO.println("updatedNames2= "+Arrays.toString(updatedNames2));

        updatedNames2[0] = "Another Name";

        IO.println("updatedNames2= "+Arrays.toString(updatedNames2));
        IO.println("names= "+Arrays.toString(names));

        updatedNames[0] = "Juliet";

//        IO.println("names= "+Arrays.toString(names));
//        IO.println("updatedNames= "+Arrays.toString(updatedNames));

        int age = Integer.parseInt("36");

    }
}


