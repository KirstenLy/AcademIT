package ru.academid.shape;

import java.util.ArrayList;

public class Main {

    public static Shape getShapeAccordingArea(ArrayList<Shape> shapeList) {
        shapeList.sort(Comparators.AreaComparator);
        return shapeList.get(0);
    }

    public static Shape getShapeAccordingPerimeter(ArrayList<Shape> shapeList) {
        shapeList.sort(Comparators.PerimeterComparator);
        return shapeList.get(1);
    }

    public static void main(String[] args) {
        ArrayList<Shape> shapeList = new ArrayList<>();

        try {
            /*
             shapeList.add(new Square(-5));
             уронит программу, если будет стоять первой. Такая же проблема, если делать не список а массивы.
             */
            shapeList.add(new Square(5));
            shapeList.add(new Square(10));
            shapeList.add(new Square(15));
            shapeList.add(new Rectangle(1, 4));
            shapeList.add(new Rectangle(12, 14));
            shapeList.add(new Rectangle(3, 7));
            shapeList.add(new Circle(3));
            shapeList.add(new Circle(6));
            shapeList.add(new Circle(9));
            shapeList.add(new Triangle(1, 2, 7, 4, 5, 6));
            shapeList.add(new Triangle(0, 0, 0, 0, 5, 6));
            shapeList.add(new Triangle(11, 3, 8, 0, 0, 0));
            shapeList.add(new Triangle(2, 0, 3, 4, 3, 6));

        } catch (Exception e) {
            System.out.println("Incorrect input");
        }

        Shape shapeWithMaxArea = getShapeAccordingArea(shapeList);
        Shape shapeWithMaxPerimeter = getShapeAccordingPerimeter(shapeList);

        System.out.printf("Максимальное значение площади среди всех фигур: %.1f%n%n", shapeWithMaxArea.getArea());

        System.out.println("Информация о фигуре с максимальной площадью:");
        System.out.println(shapeWithMaxArea.toString() + System.lineSeparator());

        System.out.printf("Фигура со вторым по величине значением периметра: %.1f%n%n", shapeWithMaxPerimeter.getPerimeter());
    }
}
