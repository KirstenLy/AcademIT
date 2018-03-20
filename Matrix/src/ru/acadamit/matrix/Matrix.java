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
            matrixElements[i] = new Vector(matrix.getVector(i));
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
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = new Vector(vectors[i]);
        }
        matrixElements = tempArray;
    }

    public int getHeight() {
        return matrixElements.length;
    }

    public int getWight() {
        return matrixElements[0].getSize();
    }

    public Vector getVector(int n) {
        if (n < 0 || n >= matrixElements.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        return new Vector(matrixElements[n]);
    }

    public void setVector(int n, Vector vector) {
        if (n < 0 || n >= matrixElements.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        matrixElements[n] = new Vector(vector);
    }

    private Vector getVectorFromRow(int n) {
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
        Vector[] tempVectors = new Vector[this.getWight()];

        for (int i = 0; i < matrixElements[0].getSize(); i++) {
            tempVectors[i] = new Vector(getVectorFromRow(i));
        }
        matrixElements = tempVectors;
    }

    public void multiplication(int n) {
        for (Vector matrixVector : matrixElements) {
            matrixVector.multiplication(n);
        }
    }

    //взято с предыдущей сданной задачи и подкорректировано
    public double determinant() {
        if (this.getHeight() != this.getWight()) {
            throw new IllegalArgumentException("Только для квадратных матриц. Текущий размер матрицы:" + this.getHeight() + "x" + this.getWight());
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
        if (this.getWight() != matrix.getWight() || this.getHeight() != matrix.getHeight()) {
            throw new IllegalArgumentException("Размерности матриц не равны.");
        }
        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i].sum(matrix.getVector(i));
        }
    }

    public void difference(Matrix matrix) {
        if (this.getWight() != matrix.getWight() || this.getHeight() != matrix.getHeight()) {
            throw new IllegalArgumentException("Размерности матриц не равны.");
        }
        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i].difference(matrix.getVector(i));
        }
    }

    public static Matrix matrixSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixElements.length != matrix2.matrixElements.length ||
                matrix1.getWight() != matrix2.getWight()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getHeight() + "x" + matrix1.getWight() + "," + matrix2.getHeight() + "x" + matrix2.getWight());
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.sum(matrix2);
        return resultMatrix;
    }

    public static Matrix matrixDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixElements.length != matrix2.matrixElements.length ||
                matrix1.getWight() != matrix2.getWight()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getHeight() + "x" + matrix1.getWight() + "," + matrix2.getHeight() + "x" + matrix2.getWight());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.difference(matrix2);
        return resultMatrix;
    }

    public static Matrix matrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixElements.length != matrix2.getWight()) {
            throw new IllegalArgumentException("Число столбцов первого сомножителя не равно числу строк второго сомножителя.");
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixElements.length, matrix1.getWight());

        double temp = 0;
        Vector tempVector1;
        Vector tempVector2;

        for (int i = 0; i < resultMatrix.matrixElements.length; i++) {
            tempVector1 = matrix1.getVector(i);
            for (int j = 0; j < resultMatrix.getWight(); j++) {
                tempVector2 = matrix2.getVectorFromRow(j);
                for (int k = 0; k < resultMatrix.getWight(); k++) {
                    temp += tempVector1.getVectorComponent(k) * tempVector2.getVectorComponent(k);
                }
                resultMatrix.matrixElements[i].setVectorComponent(j, temp);
                temp = 0;
            }
        }
        return resultMatrix;
    }

    public static Vector matrixMultiplicationOnVector(Matrix matrix, Vector vector) {
        Vector tempVector = new Vector(matrix.getWight());

        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWight(); j++) {
                tempVector.setVectorComponent(i, tempVector.getVectorComponent(i) + matrix.matrixElements[i].getVectorComponent(j) * vector.getVectorComponent(j));
            }
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
