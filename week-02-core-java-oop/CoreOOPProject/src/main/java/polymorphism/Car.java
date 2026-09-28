package polymorphism;

public class Car extends Vehicle {

    @Override
    void move() {
        IO.println("The car is moving");
    }

    @Override
    void speed(double speed) {
        IO.println("The car is speeding at "+ speed+ " miles/hour");
    }

    @Override
    void stop() {
        IO.println("The car has stopped");
    }
}
