package inheritance.interfaces;

import inheritance.Animal;

// Lab - 1: Create a Runnable interface, with a run() method
//- implement the interface in this Bird class.
public class Bird extends Animal implements Flyable {
    @Override
    public void fly() {
        IO.println("\nFlying...");
    }
}
