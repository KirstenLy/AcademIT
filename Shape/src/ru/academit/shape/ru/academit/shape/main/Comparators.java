package ru.academit.shape.ru.academit.shape.main;

import ru.academit.shape.Shape;

import java.util.Comparator;

public class Comparators {
    public static Comparator<Shape> AreaComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape shape1, Shape shape2) {

            return Double.compare(shape1.getArea(),shape2.getArea());
        }
    };

    public static Comparator<Shape> PerimeterComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape shape1, Shape shape2) {
            return Double.compare(shape1.getPerimeter(),shape2.getPerimeter());
        }
    };
}