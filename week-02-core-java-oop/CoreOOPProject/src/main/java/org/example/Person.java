package org.example;
// Records can be used as Data Transfer Objects (DTOs)
public record Person(String name, int age) {
    // Compact constructor:
    // Here, we don't have to assign values to the fields
    public Person{
        if(name== null || name.isBlank()){
            throw new IllegalArgumentException("Name required");
        }

        if(age < 0){
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
