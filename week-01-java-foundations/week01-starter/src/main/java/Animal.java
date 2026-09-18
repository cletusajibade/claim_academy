public class Animal {
    // Properties or members of this class
    String type;
    int numberOfLegs;
    String color;
    boolean hasOwner;

    // This is a constructor -- a single-line comment
    //    public Animal(){
    //        type = "Dog";
    //        numberOfLegs=4;
    //        color="brown";
    //        hasOwner=true;
    //    }

    public static void main(String[] args) {
        // Dog object
        // The "new" keyword helps to create instances of objects
        Animal dog = new Animal();

        dog.type = "Dog";
        dog.numberOfLegs = 4;
        dog.color = "brown";
        dog.hasOwner = true;

        System.out.println("---Dog Properties----");
        System.out.println("Type: "+dog.type);
        System.out.println("Number of Legs: "+dog.numberOfLegs);
        System.out.println("Color: "+dog.color);
        System.out.println("Has Owner: "+dog.hasOwner);

        // Cat
        Animal cat = new Animal();
        cat.type = "Cat";
        cat.numberOfLegs = 4;
        cat.color = "white";
        cat.hasOwner = false;

        System.out.println();

        System.out.println("---Cat Properties----");
        System.out.println("Type: "+cat.type);
        System.out.println("Number of Legs: "+cat.numberOfLegs);
        System.out.println("Color: "+cat.color);
        System.out.println("Has Owner: "+cat.hasOwner);
    }
}
