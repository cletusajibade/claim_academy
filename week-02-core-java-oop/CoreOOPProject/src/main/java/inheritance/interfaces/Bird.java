package inheritance.interfaces;

import inheritance.Animal;

// Lab - 1: Create a Runnable interface, with a run() method
//- implement the interface in this Bird class.
public class Bird extends Animal implements Flyable, Runnable {
    @Override
    public void fly() {
        IO.println("\nFlying...");
    }

    @Override
    public void run() {
        IO.println("\n Running...");
    }

    @Override
    public void stop() {
        IO.println("\n Stopped");
    }
}
