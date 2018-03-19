package ru.acadamit.matrix.ru.academit.matrix.main;

import ru.acadamit.matrix.Matrix;
import ru.academit.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {11, 22, 33}});
        Matrix matrix3 = new Matrix(matrix2);
        Matrix matrix4 = new Matrix(new Vector[]{new Vector(new double[]{111, 222, 333}), new Vector(4)});

        System.out.println("Матрица нулей 3x3:" + matrix1);

        System.out.println("Матрица из массива: " + matrix2);
        System.out.println("Её копия: " + matrix3);
        System.out.println("Её размер: " + matrix2.getSize());
        System.out.println("Её второй вектор: " + matrix2.getVector(1));
        System.out.print("Смена этого вектора на {4,4,4}: ");
        matrix2.setVector(1, new Vector(new double[]{4, 4, 4}));
        System.out.println(matrix2.toString());
        System.out.println();


        System.out.println("Матрица из массива векторов: " + matrix4);
        System.out.println();
        Matrix matrix5 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        System.out.println("matrix5: " + matrix5);
        System.out.println("Её определитель: " + matrix5.determination());
        matrix5.transpose();
        System.out.println("matrix5.transpose(): " + matrix5);
        System.out.println();

        Matrix matrix6 = new Matrix(new double[][]{{11, 22, 33}, {44, 55, 66}, {77, 88, 99}});
        Matrix matrix7 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("matrix6: " + matrix6);
        System.out.println("matrix7: " + matrix7);
        System.out.println();

        matrix6.sum(matrix7);
        System.out.println("matrix6 + matrix7: " + matrix6);
        matrix6.difference(matrix7);
        System.out.println("matrix6 + matrix7 - matrix7: " + matrix6);
        System.out.println();

        System.out.println("Статические методы.");
        Matrix matrix8 = new Matrix(new double[][]{{11, 22, 33}, {44, 55, 66}, {77, 88, 99}});
        Matrix matrix9 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("matrix8: " + matrix8);
        System.out.println("matrix9: " + matrix9);

        Matrix matrix10 = Matrix.MatrixSum(matrix8,matrix9);
        Matrix matrix11 = Matrix.MatrixDiss(matrix8,matrix9);
        System.out.println("matrix8 + matrix9: " + matrix10);
        System.out.println("matrix8 - matrix9: " + matrix11);

    }
}
