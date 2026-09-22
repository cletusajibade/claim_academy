package inheritance.sealed;

public final class Tricycle extends Vehicle{
    public String name;

    public Tricycle(String NewName){
        this.name = NewName;
    }

    public String getName() {
        return name;
    }
}
