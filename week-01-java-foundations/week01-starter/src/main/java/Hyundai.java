public class Hyundai extends Car {
    public static void main(String[] args) {
        Hyundai car = new Hyundai();
        car.make = "Hyundai";
        car.brand = "Palisade";
        car.engineType = "Turbo";
        car.year = 2020;

        System.out.println(car.make);
        System.out.println(car.brand);
        System.out.println(car.year);
        System.out.println(car.engineType);
    }
}
