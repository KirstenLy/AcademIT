import java.util.Objects;

public class Square extends Shape implements IShape {
    private double a;

    public Square(double a) {
        if (a > 0) {
            this.a = a;
        }
    }

    @Override
    public double getWidth() {
        return a;
    }

    @Override
    public double getHeight() {
        return a;
    }

    @Override
    public double getArea() {
        return a * a;
    }

    @Override
    public double getPerimeter() {
        return 4 * a;
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
        Square square = (Square) o;
        return Double.compare(square.a, a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}
