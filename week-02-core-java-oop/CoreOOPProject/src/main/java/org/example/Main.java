package org.example;

import inheritance.Dog;
import inheritance.interfaces.Bird;
import inheritance.sealed.Bicycle;
import inheritance.sealed.Tricycle;

import java.util.ArrayList;

public class Main {
    static void main() {
        Person person = new Person("James", 20);
        IO.println("Person Name: " + person.name());
        IO.println("Person Age: " + person.age());

        Rectangle rectangle = new Rectangle(4.6, 7.8);
        IO.println("\nArea of the rectangle = "+ rectangle.area());
        IO.println("Perimeter of the rectangle = "+ rectangle.perimeter());

        IO.println("Width ="+ rectangle.width());
        IO.println("Height ="+ rectangle.height());

        Animal animal = new Animal();

        Roster roster = new Roster(new ArrayList<>());
        roster.names().add("Eddy");
        roster.names().add("Juliet");
        IO.println(roster.names());

        IO.println();

        Box<String> b = new Box<String>("This is generic types in Java");
        IO.println(b.getValue());

        Box<Integer> b2 = new Box<>(5);
        Box<Boolean> b3 = new Box<>(true);

        b3.setValue(false);

        Dog d = new Dog();
        d.bark();
        d.eat();
        d.walk(20);

        Bird bird = new Bird();
        bird.walk(30);
        bird.fly();
        bird.eat();

        IO.println();

        Bicycle bicycle = new Bicycle("Bicycle");
        IO.print(bicycle.getName()+" ");
        bicycle.wheels(2);

        Tricycle tricycle = new Tricycle("Tricycle");
        IO.print(tricycle.getName()+" ");
        tricycle.wheels(3);
    }
}
