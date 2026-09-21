package org.example;

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
    }
}
