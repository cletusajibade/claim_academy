public class Main {
    public static void main(String[] args) {
        Car a = new Car("Civic");
        Car b = new Car("Corolla");
        a.accelerate(10);
        System.out.println(a.getSpeed());
        System.out.println(b.getSpeed());
    }
}
