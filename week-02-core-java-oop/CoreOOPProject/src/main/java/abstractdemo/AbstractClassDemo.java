package abstractdemo;

public class AbstractClassDemo {
    static void main(String[] args) {
        // We will create instances of the children
        // and run them here.

        // "is-a" relationship
        //Rectangle rectangle2 = new Rectangle("Rectangle", 3.4, 5);
        Shape rectangle = new Rectangle("Rectangle", 4, 5);
        rectangle.printDetails();

        Circle circle = new Circle("Circle", 2);
        circle.printDetails();
    }
}

// We are defining shared or common members that subclasses
// can inherit.
// Note: You cannot directly create an instance of an abstract class
// i.e "Shape s = new Shape();" is not possible.
abstract class Shape {
    private final String name;

    protected Shape(String name) {
        this.name = name;
    }

    public abstract double area();

    public String getName() {
        return name;
    }

    public void printDetails() {
        IO.println(name + ", area = " + area());
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String newName, double width, double height) {
        super(newName);

        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Circle extends Shape {
    private final double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

