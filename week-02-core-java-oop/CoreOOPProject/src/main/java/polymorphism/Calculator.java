package polymorphism;

public class Calculator {
    int add (int a, int b){
        return a + b;
    }

    double add (double a, double b){
        return a + b;
    }

    // Not necessary since the one above can accept both double and int.
    double add (double a, int b){
        return a + b;
    }
}
