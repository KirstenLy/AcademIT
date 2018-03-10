public class Main {

    public static void main(String[] args) {

        Shape square1 = new Square(5);
        Shape square2 = new Square(10);
        Shape square3 = new Square(15);

        Shape rectangle1 = new Rectangle(1, 4);
        Shape rectangle2 = new Rectangle(12, 14);
        Shape rectangle3 = new Rectangle(3, 7);
        Shape rectangle4 = new Rectangle(3, 7);

        Shape circle1 = new Circle(3);
        Shape circle2 = new Circle(6);
        Shape circle3 = new Circle(9);

        Shape triangle1 = new Triangle(1, 2, 3, 4, 5, 6);
        Shape triangle2 = new Triangle(11, 3, 8, 0, 0, 0);
        Shape triangle3 = new Triangle(2, 0, 3, 4, 3, 6);

        Shape resultShape = Shape.getShapeAccordingArea(rectangle1, rectangle2, rectangle3, square1, square2, square3, circle1, circle2, circle3, triangle1, triangle2, triangle3);
        Shape resultShape1 = Shape.getShapeAccordingPerimeter(rectangle1, rectangle2, rectangle3, square1, square2, square3, circle1, circle2, circle3, triangle1, triangle2, triangle3);

        System.out.printf("Максимальное значение площади среди всех фигур: %.1f%n%n", resultShape.getArea());

        System.out.println("Информация о фигуре с максимальной площадью:");
        System.out.println(resultShape.toString() + System.lineSeparator());

        System.out.printf("Фигура со вторым по величине значением периметра: %.1f%n%n", resultShape1.getPerimeter());

        System.out.println("Проверка переопределения метода equals: " + rectangle3.equals(rectangle4));
        System.out.println("Проверка переопределения метода hashCode: " + (rectangle3.hashCode() == rectangle4.hashCode()));

    }
}
