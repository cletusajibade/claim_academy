public class Car {
    String model;
    int speed;

    public Car(String model) {
        this.model = model;
        speed = 0;
    }

    void accelerate(int amount) {
        speed += amount; //speed=0 + 10
    }

    int getSpeed() {
        return speed;
    }

    void stop() {
        speed = 0;
    }
}
