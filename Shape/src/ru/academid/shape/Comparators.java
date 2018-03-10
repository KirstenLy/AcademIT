package ru.academid.shape;

import java.util.Comparator;

public class Comparators {
    public static Comparator<Shape> AreaComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape shape1, Shape shape2) {
            return (int) (shape2.getArea() - shape1.getArea());
        }
    };

    public static Comparator<Shape> PerimeterComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape shape1, Shape shape2) {
            return (int) (shape2.getPerimeter() - shape1.getPerimeter());
        }
    };
}