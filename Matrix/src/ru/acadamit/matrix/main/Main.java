package ru.acadamit.matrix.main;

import ru.acadamit.matrix.Matrix;
import ru.academit.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {11, 22, 33}
        });
        Matrix matrix3 = new Matrix(matrix2);
        Matrix matrix4 = new Matrix(new Vector[]{
                new Vector(new double[]{3, 6, 9}),
                new Vector(3)
        });

        System.out.println("Матрица нулей размером " + matrix1.getRowsNumber() + "x" + matrix1.getColumnsNumber() + ": " + matrix1);

        System.out.println("Матрица из double массива: " + matrix2);
        System.out.println("Её копия: " + matrix3);
        System.out.println("Её размер: " + matrix2.getRowsNumber() + "x" + matrix2.getColumnsNumber());
        System.out.println("Её второй вектор: " + matrix2.getRow(1));

        System.out.print("Смена этого вектора на {4,4,4}: ");
        matrix2.setRow(1, new Vector(new double[]
                {4, 4, 4}
        ));
        System.out.println(matrix2);

        System.out.print("Умножение этой матрицы на 10: ");
        matrix2.multiplication(10);
        System.out.println(matrix2);
        System.out.println();

        System.out.println("Матрица из массива векторов: " + matrix4);
        System.out.println();

        Matrix matrix5 = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        System.out.println("matrix5: " + matrix5);
        System.out.println("matrix5.determination: " + matrix5.determinant());
        matrix5.transpose();
        System.out.println("matrix5.transpose: " + matrix5);
        System.out.println();

        Matrix matrix6 = new Matrix(new double[][]{
                {3, 6, 9},
                {12, 15, 18},
                {21, 24, 27}
        });
        Matrix matrix7 = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        System.out.println("matrix6: " + matrix6);
        System.out.println("matrix7: " + matrix7);
        System.out.println();

        matrix6.sum(matrix7);
        System.out.println("matrix6 + matrix7: " + matrix6);
        matrix6.difference(matrix7);
        System.out.println("matrix6 + matrix7 - matrix7: " + matrix6);
        System.out.println();

        System.out.println("Статические методы.");
        Matrix matrix8 = new Matrix(new double[][]{
                {3, 6, 9},
                {12, 15, 18},
                {21, 24, 27}
        });
        Matrix matrix9 = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
        System.out.println("matrix8: " + matrix8);
        System.out.println("matrix9: " + matrix9);

        Matrix matrix10 = Matrix.matrixSum(matrix8, matrix9);
        Matrix matrix11 = Matrix.matrixDifference(matrix8, matrix9);
        Matrix matrix12 = Matrix.matrixMultiplication(matrix8, matrix9);
        System.out.println("matrix8 + matrix9: " + matrix10);
        System.out.println("matrix8 - matrix9: " + matrix11);
        System.out.println("matrix8 * matrix9: " + matrix12);

        Vector vector11 = new Vector(new double[]
                {10, 10, 10}
        );
        System.out.println("vector11: " + vector11);
        System.out.println("matrix9 * vector11: " + Matrix.matrixMultiplicationOnVector(matrix9, vector11));

        Matrix matrix64 = new Matrix(new double[][]{
                {3,3},
                {3,1}

        });

        System.out.println(matrix64.determinant());
    }
}