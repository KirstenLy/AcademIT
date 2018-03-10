import java.util.Objects;

public class Rectangle extends Shape implements IShape {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        if (a > 0 & b > 0) {
            this.a = a;
            this.b = b;
        }
    }

    @Override
    public double getWidth() {
        return a;
    }

    @Override
    public double getHeight() {
        return b;
    }

    @Override
    public double getArea() {
        return a * b;
    }

    @Override
    public double getPerimeter() {
        return 2 * a + 2 * b;
    }

    @Override
    public String toString() {
        return String.format("Тип фигуры:%s%nДлинна:%.1f%nШирина:%.1f%nПериметр:%.1f%nПлощадь:%.1f", this.getClass().getName(), this.getWidth(), this.getHeight(), this.getPerimeter(), this.getArea());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.a, a) == 0 &&
                Double.compare(rectangle.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
