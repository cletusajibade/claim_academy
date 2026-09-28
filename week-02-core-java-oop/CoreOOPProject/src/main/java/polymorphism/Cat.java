package polymorphism;

public class Cat extends Animal{
    @Override
    public void makeSound(String name) {
        super.makeSound(name);
        IO.println("Meow! meow!!");
    }
}
