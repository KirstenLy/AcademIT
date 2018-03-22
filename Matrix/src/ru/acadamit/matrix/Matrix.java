package ru.acadamit.matrix;

import ru.academit.vector.Vector;

public class Matrix {
    private Vector[] matrixElements;

    public Matrix(int height, int wight) {
        if (height <= 0) {
            throw new IllegalArgumentException("Некорректно задана высота: " + height);
        }
        if (wight <= 0) {
            throw new IllegalArgumentException("Некорректно задана ширина: " + wight);
        }

        matrixElements = new Vector[height];

        for (int i = 0; i < height; i++) {
            matrixElements[i] = new Vector(wight);
        }
    }

    public Matrix(Matrix matrix) {
        matrixElements = new Vector[matrix.matrixElements.length];
        for (int i = 0; i < matrix.matrixElements.length; i++) {
            matrixElements[i] = new Vector(matrix.getRow(i));
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

        matrixElements = new Vector[doubleArray.length];

        for (int i = 0; i < doubleArray.length; i++) {
            matrixElements[i] = new Vector(vectorsLength, doubleArray[i]);
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
        matrixElements = tempArray;
    }

    public int getRowsNumber() {
        return matrixElements.length;
    }

    public int getColumnsNumber() {
        return matrixElements[0].getSize();
    }

    public Vector getRow(int n) {
        if (n < 0 || n >= matrixElements.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        return new Vector(matrixElements[n]);
    }

    public void setRow(int n, Vector vector) {
        if (n < 0 || n >= matrixElements.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        matrixElements[n] = new Vector(vector);
    }

    public Vector getColumn(int n) {
        if (n < 0 || n >= matrixElements[0].getSize()) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        double[] temp = new double[matrixElements.length];

        for (int i = 0; i < matrixElements.length; i++) {
            temp[i] = matrixElements[i].getVectorComponent(n);
        }
        return new Vector(temp);
    }

    public void transpose() {
        Vector[] tempVectors = new Vector[this.getColumnsNumber()];

        for (int i = 0; i < this.getColumnsNumber(); i++) {
            tempVectors[i] = new Vector(getColumn(i));
        }
        matrixElements = tempVectors;
    }

    public void multiplication(int n) {
        for (Vector matrixVector : matrixElements) {
            matrixVector.multiplication(n);
        }
    }

    public double determinant() {
        if (this.getRowsNumber() != this.getColumnsNumber()) {
            throw new IllegalArgumentException("Только для квадратных матриц. Текущий размер матрицы:" + this.getRowsNumber() + "x" + this.getColumnsNumber());
        }

        double epsilon = 1E-5;
        int swapStringsCount = 0;

        Matrix tempMatrix = new Matrix(matrixElements.length, matrixElements.length);

        for (int i = 0; i < matrixElements.length; i++) {
            tempMatrix.matrixElements[i] = new Vector(matrixElements[i]);
        }

        for (int i = 0; i < tempMatrix.matrixElements.length - 1; i++) {
            for (int j = i + 1; j < tempMatrix.matrixElements.length; j++) {
                if (Math.abs(tempMatrix.matrixElements[i].getVectorComponent(i)) < epsilon) {
                    for (int p = j; p < tempMatrix.matrixElements.length; p++) {
                        if (Math.abs(tempMatrix.matrixElements[p].getVectorComponent(i)) >= epsilon) {
                            swapTwoStringsInMatrix(tempMatrix.matrixElements, i, p);
                            swapStringsCount++;
                            break;
                        }
                    }
                }

                if (Math.abs(tempMatrix.matrixElements[j].getVectorComponent(i)) < epsilon) {
                    continue;
                }

                double multiplier = tempMatrix.matrixElements[j].getVectorComponent(i) / tempMatrix.matrixElements[i].getVectorComponent(i);
                for (int k = i; k < tempMatrix.matrixElements.length; k++) {
                    tempMatrix.matrixElements[j].setVectorComponent(k, tempMatrix.matrixElements[j].getVectorComponent(k) - tempMatrix.matrixElements[i].getVectorComponent(k) * multiplier);
                }
            }
        }

        double determinant = 1;
        for (int i = 0; i < tempMatrix.matrixElements.length; i++) {
            determinant *= tempMatrix.matrixElements[i].getVectorComponent(i);
        }

        return Math.abs((swapStringsCount % 2 == 0 ? 1 : -1) * determinant);
    }

    public void sum(Matrix matrix) {
        if (this.getColumnsNumber() != matrix.getColumnsNumber() || this.getRowsNumber() != matrix.getRowsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны.");
        }
        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i].sum(matrix.matrixElements[i]);
        }
    }

    public void difference(Matrix matrix) {
        if (this.getColumnsNumber() != matrix.getColumnsNumber() || this.getRowsNumber() != matrix.getRowsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны.");
        }
        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i].difference(matrix.matrixElements[i]);
        }
    }

    public static Matrix matrixSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixElements.length != matrix2.matrixElements.length ||
                matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getRowsNumber() + "x" + matrix1.getColumnsNumber() + "," + matrix2.getRowsNumber() + "x" + matrix2.getColumnsNumber());
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.sum(matrix2);
        return resultMatrix;
    }

    public static Matrix matrixDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixElements.length != matrix2.matrixElements.length ||
                matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getRowsNumber() + "x" + matrix1.getColumnsNumber() + "," + matrix2.getRowsNumber() + "x" + matrix2.getColumnsNumber());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.difference(matrix2);
        return resultMatrix;
    }

    public static Matrix matrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.matrixElements.length) {
            throw new IllegalArgumentException("Число столбцов первого сомножителя не равно числу строк второго сомножителя.");
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixElements.length, matrix2.getColumnsNumber());

        for (int i = 0; i < resultMatrix.matrixElements.length; i++) {
            Vector tempVector1 = matrix1.getRow(i);

            for (int j = 0; j < resultMatrix.getColumnsNumber(); j++) {
                Vector tempVector2 = matrix2.getColumn(j);
                double temp = 0;
                for (int k = 0; k < tempVector1.getSize(); k++) {
                    temp += tempVector1.getVectorComponent(k) * tempVector2.getVectorComponent(k);
                }
                resultMatrix.matrixElements[i].setVectorComponent(j, temp);
            }
        }
        return resultMatrix;
    }

    public static Vector matrixMultiplicationOnVector(Matrix matrix, Vector vector) {
        if (matrix.getColumnsNumber() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов в матрице не совпадает с числом строк в векторе-столбце.");
        }
        Vector tempVector = new Vector(matrix.getColumnsNumber());

        for (int i = 0; i < matrix.getColumnsNumber(); i++) {
            tempVector.setVectorComponent(i, Vector.vectorsScalarMultiplication(matrix.matrixElements[i], vector));
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
        for (Vector aMatrixArray : matrixElements) {
            result.append(aMatrixArray.toString()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        result.append("}");
        return String.valueOf(result);
    }
}
