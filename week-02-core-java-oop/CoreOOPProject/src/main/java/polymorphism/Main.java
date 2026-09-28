package polymorphism;

public class Main {
    static void main(String[] args) {
        Calculator calculator = new Calculator();
        IO.println(calculator.add(3,2));
        IO.println(calculator.add(4.5,2.7));
        IO.println(calculator.add(3,2.7));

        Dog d = new Dog();
        d.makeSound("Dog");

        Cat cat = new Cat();
        cat.makeSound("Cat");

        Animal animal = new Dog();
        animal.makeSound("Dog");

        Animal animal2 = new Cat();
        animal2.makeSound("Cat");

        // A Shoe is not a type of Animal
        //Animal animal3 = new Shoe();

        Notification notification = new EmailNotification();
        notification.send();

        Notification notification2 = new SMSNotification();
        notification2.send();

        EmailNotification emailNotification = new EmailNotification();
        emailNotification.send();

        IO.println();

        Vehicle vehicle1 = new Car();
        vehicle1.move();
        vehicle1.speed(6.5);
        vehicle1.stop();
    }
}
