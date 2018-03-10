package ru.academid.shape;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double line1;
    private double line2;
    private double line3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if ((x3 - x1) / (x2 - x1) == (y3 - y1) / (y2 - y1) || ((x1 == x2 && y1 == y2) || (x2 == x3 && y2 == y3) || (x3 == x1 && y3 == y1))) {
            throw new IllegalArgumentException();
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.line1 = getLineLength(x1, y1, x2, y2);
        this.line2 = getLineLength(x2, y2, x3, y3);
        this.line3 = getLineLength(x3, y3, x1, y1);
    }

    private static double getLineLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getWidth() {
        return line1;
    }

    @Override
    public double getHeight() {
        return 2 * getArea() / line1;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - line1) * (p - line2) * (p - line3));
    }

    @Override
    public double getPerimeter() {
        return line1 + line2 + line3;
    }

    @Override
    public String toString() {
        return String.format("Тип фигуры:%s%nДлинна(x2-x1;y2-y1):%.1f%nВысота, проведённая к этой длинне:%.1f%nПериметр:%.1f%nПлощадь:%.1f", this.getClass().getName(), this.getWidth(), this.getHeight(), this.getPerimeter(), this.getArea());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 &&
                Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 &&
                Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
