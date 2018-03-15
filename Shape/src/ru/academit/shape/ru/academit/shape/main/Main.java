package ru.academit.shape.ru.academit.shape.main;

import ru.academit.shape.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static Shape getShapeAccordingArea(Shape[] shapeArray) {
        Shape[] sortShapes = Arrays.copyOf(shapeArray, shapeArray.length);
        Arrays.sort(sortShapes, Comparators.AreaComparator);
        return sortShapes[0];
    }

    public static Shape getShapeAccordingPerimeter(Shape[] shapeArray) {
        Shape[] sortShapes = Arrays.copyOf(shapeArray, shapeArray.length);
        Arrays.sort(sortShapes, Comparators.PerimeterComparator);
        return sortShapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapesArray = new Shape[]{
                new Square(5),
                new Square(10),
                new Square(15),
                new Rectangle(1, 4),
                new Rectangle(12, 14),
                new Rectangle(3, 7),
                new Circle(3),
                new Circle(6),
                new Circle(9),
                new Triangle(1, 2, 7, 4, 5, 6),
                new Triangle(11, 3, 8, 0, 0, 0),
                new Triangle(2, 0, 3, 4, 3, 6)
        };

        Shape shapeWithMaxArea = getShapeAccordingArea(shapesArray);
        Shape shapeWithMaxPerimeter = getShapeAccordingPerimeter(shapesArray);

        System.out.printf("Максимальное значение площади среди всех фигур: %.1f%n%n", shapeWithMaxArea.getArea());

        System.out.println("Информация о фигуре с максимальной площадью:");
        System.out.println(shapeWithMaxArea.toString() + System.lineSeparator());

        System.out.printf("Фигура со вторым по величине значением периметра: %.1f%n%n", shapeWithMaxPerimeter.getPerimeter());
    }
}
