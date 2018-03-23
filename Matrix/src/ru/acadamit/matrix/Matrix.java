package ru.acadamit.matrix;

import ru.academit.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int height, int wight) {
        if (height <= 0) {
            throw new IllegalArgumentException("Некорректно задана высота: " + height);
        }
        if (wight <= 0) {
            throw new IllegalArgumentException("Некорректно задана ширина: " + wight);
        }

        rows = new Vector[height];

        for (int i = 0; i < height; i++) {
            rows[i] = new Vector(wight);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.getRow(i));
        }
    }

    public Matrix(double[][] doubleArray) {
        if (doubleArray.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }

        int vectorsLength = 0;

        for (double[] vector : doubleArray) {
            vectorsLength = Math.max(vectorsLength, vector.length);
        }

        if (vectorsLength == 0) {
            throw new IllegalArgumentException("Длинна максимального элемента равна нулю.");
        }

        rows = new Vector[doubleArray.length];

        for (int i = 0; i < doubleArray.length; i++) {
            rows[i] = new Vector(vectorsLength, doubleArray[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }
        Vector[] tempArray = new Vector[vectors.length];

        int maxVectorSize = 0;
        for (Vector vector : vectors) {
            maxVectorSize = Math.max(maxVectorSize, vector.getSize());
        }

        for (int i = 0; i < tempArray.length; i++) {
            if (vectors[i].getSize() == maxVectorSize) {
                tempArray[i] = new Vector(vectors[i]);
            } else {
                tempArray[i] = new Vector(maxVectorSize, vectors[i]);
            }
        }
        rows = tempArray;
    }

    public int getRowsNumber() {
        return rows.length;
    }

    public int getColumnsNumber() {
        return rows[0].getSize();
    }

    public Vector getRow(int n) {
        if (n < 0 || n >= rows.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        return new Vector(rows[n]);
    }

    public void setRow(int n, Vector vector) {
        if (n < 0 || n >= rows.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        rows[n] = new Vector(vector);
    }

    public Vector getColumn(int n) {
        if (n < 0 || n >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        double[] temp = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            temp[i] = rows[i].getVectorComponent(n);
        }
        return new Vector(temp);
    }

    public void transpose() {
        Vector[] tempVectors = new Vector[this.getColumnsNumber()];

        for (int i = 0; i < this.getColumnsNumber(); i++) {
            tempVectors[i] = new Vector(getColumn(i));
        }
        rows = tempVectors;
    }

    public void multiplication(int n) {
        for (Vector matrixVector : rows) {
            matrixVector.multiplication(n);
        }
    }

    public double determinant() {
        if (this.getRowsNumber() != this.getColumnsNumber()) {
            throw new IllegalArgumentException("Только для квадратных матриц. Текущий размер матрицы:" + this.getRowsNumber() + "x" + this.getColumnsNumber());
        }

        double epsilon = 1E-5;
        int swapStringsCount = 0;

        Matrix tempMatrix = new Matrix(rows.length, rows.length);

        for (int i = 0; i < rows.length; i++) {
            tempMatrix.rows[i] = new Vector(rows[i]);
        }

        for (int i = 0; i < tempMatrix.rows.length - 1; i++) {
            for (int j = i + 1; j < tempMatrix.rows.length; j++) {
                if (Math.abs(tempMatrix.rows[i].getVectorComponent(i)) < epsilon) {
                    for (int p = j; p < tempMatrix.rows.length; p++) {
                        if (Math.abs(tempMatrix.rows[p].getVectorComponent(i)) >= epsilon) {
                            swapTwoStringsInMatrix(tempMatrix.rows, i, p);
                            swapStringsCount++;
                            break;
                        }
                    }
                }

                if (Math.abs(tempMatrix.rows[j].getVectorComponent(i)) < epsilon) {
                    continue;
                }

                double multiplier = tempMatrix.rows[j].getVectorComponent(i) / tempMatrix.rows[i].getVectorComponent(i);
                for (int k = i; k < tempMatrix.rows.length; k++) {
                    tempMatrix.rows[j].setVectorComponent(k, tempMatrix.rows[j].getVectorComponent(k) - tempMatrix.rows[i].getVectorComponent(k) * multiplier);
                }
            }
        }

        double determinant = 1;
        for (int i = 0; i < tempMatrix.rows.length; i++) {
            determinant *= tempMatrix.rows[i].getVectorComponent(i);
        }

        return Math.abs((swapStringsCount % 2 == 0 ? 1 : -1) * determinant);
    }

    public void sum(Matrix matrix) {
        if (this.getColumnsNumber() != matrix.getColumnsNumber() || this.getRowsNumber() != matrix.getRowsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны.");
        }
        for (int i = 0; i < rows.length; i++) {
            rows[i].sum(matrix.rows[i]);
        }
    }

    public void difference(Matrix matrix) {
        if (this.getColumnsNumber() != matrix.getColumnsNumber() || this.getRowsNumber() != matrix.getRowsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны.");
        }
        for (int i = 0; i < rows.length; i++) {
            rows[i].difference(matrix.rows[i]);
        }
    }

    public static Matrix matrixSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length ||
                matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getRowsNumber() + "x" + matrix1.getColumnsNumber() + "," + matrix2.getRowsNumber() + "x" + matrix2.getColumnsNumber());
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.sum(matrix2);
        return resultMatrix;
    }

    public static Matrix matrixDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length ||
                matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getRowsNumber() + "x" + matrix1.getColumnsNumber() + "," + matrix2.getRowsNumber() + "x" + matrix2.getColumnsNumber());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.difference(matrix2);
        return resultMatrix;
    }

    public static Matrix matrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов первого сомножителя не равно числу строк второго сомножителя.");
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsNumber());

        for (int i = 0; i < resultMatrix.rows.length; i++) {
            Vector tempVector1 = matrix1.getRow(i);

            for (int j = 0; j < resultMatrix.getColumnsNumber(); j++) {
                Vector tempVector2 = matrix2.getColumn(j);
                double temp = 0;
                for (int k = 0; k < tempVector1.getSize(); k++) {
                    temp += tempVector1.getVectorComponent(k) * tempVector2.getVectorComponent(k);
                }
                resultMatrix.rows[i].setVectorComponent(j, temp);
            }
        }
        return resultMatrix;
    }

    public static Vector matrixMultiplicationOnVector(Matrix matrix, Vector vector) {
        if (matrix.getColumnsNumber() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов в матрице не совпадает с числом строк в векторе-столбце.");
        }
        Vector tempVector = new Vector(matrix.getRowsNumber());

        for (int i = 0; i < matrix.getRowsNumber(); i++) {
            tempVector.setVectorComponent(i, Vector.vectorsScalarMultiplication(matrix.rows[i], vector));
        }
        return tempVector;
    }

    private void swapTwoStringsInMatrix(Vector[] matrixArray, int firstRow, int secondRow) {
        Vector temp = matrixArray[firstRow];
        matrixArray[firstRow] = matrixArray[secondRow];
        matrixArray[secondRow] = temp;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (Vector aMatrixArray : rows) {
            result.append(aMatrixArray.toString()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        result.append("}");
        return String.valueOf(result);
    }
}
