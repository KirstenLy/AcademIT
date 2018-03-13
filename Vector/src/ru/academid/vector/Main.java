package ru.academid.vector;

public class Main {
    public static void main(String[] args) {

        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(new double[]{3, 9.3, 44});
        Vector vector3 = new Vector(3, new double[]{7, 5, 2.3});
        Vector vector4 = new Vector(vector3);

        System.out.println("Инициализация:");
        System.out.println("Vector(n): " + vector1.toString());
        System.out.println("Vector(n).getSize(): " + vector1.getSize());
        System.out.println("Vector(double[]): " + vector2.toString());
        System.out.println("Vector(n,double[]): " + vector3.toString());
        System.out.println("Vector(Vector3): " + vector4.toString() + System.lineSeparator());


        System.out.println("Нестатические методы:");

        System.out.print("Сумма: " + vector2.toString() + " + " + vector4.toString() + " = ");
        vector2.sum(vector4);
        System.out.println(vector2.toString());

        System.out.print("Разность: " + vector2.toString() + " - " + vector4.toString() + "=");
        vector2.difference(vector4);
        System.out.println(vector2.toString());

        System.out.print("Умножение на скаляр: " + vector3.toString() + " * 3 = ");
        vector3.multiplication(3);
        System.out.println(vector3.toString());

        System.out.print("Cпин: " + vector3.toString() + " = ");
        vector3.spin();
        System.out.println(vector3.toString());

        System.out.printf("Длинна: %s = %.1f%n%n", vector3.toString(), vector3.length());

        System.out.println("Cтатические методы:");

        Vector vector5 = new Vector(new double[]{5, 10, 15});
        Vector vector6 = new Vector(new double[]{20, 25, 30});

        Vector vector7 = Vector.vectorsSum(vector5, vector6);
        Vector vector8 = Vector.vectorsDifference(vector5, vector6);
        double scalarDifference = Vector.vectorsScalarMultiplication(vector5, vector6);

        System.out.println("Сумма: " + vector5.toString() + " + " + vector6.toString() + " = " + vector7.toString());
        System.out.println("Разность: " + vector5.toString() + " - " + vector6.toString() + " = " + vector8.toString());
        System.out.println("Скалярное произведение: " + vector5.toString() + " * " + vector6.toString() + " = " + scalarDifference);

        Vector vector9 = new Vector(new double[]{5, 10, 15});
        System.out.println("Equals: " + vector5.toString() + "  " + vector9.toString() + " = " + vector5.equals(vector9));
    }
}
