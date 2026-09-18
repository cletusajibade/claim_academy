public class Car {
    String model;
    int speed;

    public Car(String model) {
        this.model = model;
    }

    void accelerate(int amount) {
        speed += amount;
    }

    int getSpeed() {
        return speed;
    }
}
