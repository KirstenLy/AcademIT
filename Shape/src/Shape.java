import java.util.Arrays;

public class Shape implements IShape{
    public static Shape getShapeAccordingArea(Shape... shapes) {
        Arrays.sort(shapes, Comparators.AreaComparator);
        return shapes[0];
    }

    public static Shape getShapeAccordingPerimeter(Shape... shapes) {
        Arrays.sort(shapes, Comparators.PerimeterComparator);
        return shapes[0];
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public double getArea() {
        return 0;
    }
}
