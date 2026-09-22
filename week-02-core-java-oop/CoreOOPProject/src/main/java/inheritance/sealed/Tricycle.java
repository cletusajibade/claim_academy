package inheritance.sealed;

public final class Tricycle extends Vehicle{
    public String name;

    public Tricycle(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
