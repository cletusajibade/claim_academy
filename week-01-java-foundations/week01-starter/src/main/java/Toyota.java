public class Toyota extends Car{
    public static void main(String[] args) {
        Toyota car = new Toyota();
        car.make = "Toyota";
        car.brand = "Highlander";
        car.year = 1981;
        car.engineType = "Turbo";

        System.out.println(car.make);
        System.out.println(car.brand);
        System.out.println(car.year);
        System.out.println(car.engineType);
    }
}