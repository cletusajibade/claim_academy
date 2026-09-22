package org.example;

// This is a generic class annotation.
// The objects will need to fill in the actual type
// The convention is to use letter "T"
public class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T newValue){
        this.value = newValue;
    }

}
