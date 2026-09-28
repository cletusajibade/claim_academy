package polymorphism;

public class Dog extends Animal{

    @Override
    public void makeSound(String name) {
        super.makeSound(name);
        IO.println("Woof! woof!!");
    }
}
