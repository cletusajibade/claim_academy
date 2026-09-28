package polymorphism.shapeslab;

interface Shape {
    double area();
}

class Rectangle implements Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Circle implements Shape{
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public class PolymorphismLab {
    static void main(String[] args) {
//        Shape[] shapes = {
//                new Rectangle(4,7),
//                new Circle(3)
//        };
//
//        for(Shape s: shapes){
//            IO.println(s.area());
//        }

        Rectangle rectangle = new Rectangle(5,3);
        IO.println(rectangle.area());

        Circle circle = new Circle(3);
        IO.println(circle.area());

    }
}
