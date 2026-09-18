public class Main {
    static void main(String[] args) {
        Car car1 = new Car("Civic");
        car1.accelerate(10);
        int speed = car1.getSpeed();
        IO.println("Car1 Speed = " + speed);

        // Increase the speed of Car1 by 15
        car1.accelerate(15);
        int currentSpeed = car1.getSpeed();
        IO.println("Car1 new speed = " + currentSpeed);

        Car car2 = new Car("Civic");
        car2.accelerate(15);
        IO.println("Car2 speed = "+car2.getSpeed());

        // Stop car1
        car1.stop();
        IO.println("Car 1 stops, speed is: "+car1.getSpeed());

        // Reference variables
        // Two reference variables can share 1 object
        Car car3 = car1;
        IO.println(car1);
        IO.println(car3);
        Car car4 = car1;

        Car car5 = null;
        car5 = car3;
        car5.getSpeed();

        IO.println(car1);
        IO.println(car2);
    }
}
