package inheritance.sealed;

public final class Bicycle extends Vehicle {
    public String name;

    public Bicycle(String newName) {
        this.name = newName;
    }

    public String getName(){
        return name;
    }
}
