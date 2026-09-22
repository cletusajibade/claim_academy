package inheritance.sealed;

// This is a sealed class
public sealed class Vehicle permits Car, Bicycle, Tricycle {
    public void wheels(int numberOfWheels){
        IO.println("Object has "+ numberOfWheels+" wheels.");
    }
}
