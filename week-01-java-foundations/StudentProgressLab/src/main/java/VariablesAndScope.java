public class VariablesAndScope {
    static void main() {
        var age = 25;
        var name = "John";

        //System.out.println(age);
        //IO.println(age); //Java 25
//
//        System.out.println(name);
//        IO.println(name); //Java 25

        int score = 87;
        double expandedVariable = score;
//        IO.println(score);
//        IO.println(expandedVariable);

        double amount = 24.99;
        int amountTruncated = (int) amount;
//        IO.println(amount);
//        IO.println(amountTruncated);

//        ARRAYS
        int[] numbers = {85, 90, 95};
//        IO.println(numbers[0]);
//        IO.println(numbers[1]);
//        IO.println(numbers[2]);

        IO.println();

//        Printing arrays with FOR LOOP
//        for(int i=0; i<numbers.length; i++){
//            IO.println(i+": "+numbers[i]);
//        }

        IO.println();
//        IO.println("Array length: "+numbers.length);

        int[] scores = {80, 85, 90, 95, 100};
        int firstItem = scores[0];
        int lastIndex = scores.length - 1;
        int lastItem = scores[lastIndex];

//        IO.println(firstItem);
//        IO.println(lastItem);

//        int score5 = scores[5];
//        IO.println(score5);

//        Arithmetic and Assignment
        int quotient = 7 / 2;
        IO.println(quotient);
        double result = 7 / 2.0;
        IO.println(result);

        int remainder = 7 % 2;
        IO.println(remainder);

        double y = 5;
//        double x = y;
//        IO.println(x);
        y *= 2; //y=5*2=10
        y += 6; //y=10+6
        y /= 3.0;
        IO.println(y);

        int x = 15;
        if (x != 10){
            IO.println(x+ " is not equal to "+10);
        }

        if (x <=20){
            IO.println("X is less than or equal to 20");
        }

        if (x > 10 && y > 8){
            IO.println("This an AND operation");
        }

        if (x > 20 || y > 8){
            IO.println("This is an OR operation");
            IO.println("Either operand is TRUE");
        }
        else{
            IO.println("both operands are FALSE");
        }
    }
}
