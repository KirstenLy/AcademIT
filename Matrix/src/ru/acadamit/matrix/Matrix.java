package ru.acadamit.matrix;

import ru.academit.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixArray;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Некорректные значения n или m: " + n + ";" + m);
        }

        matrixArray = new Vector[n];
        for (int i = 0; i < n; i++) {
            matrixArray[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Передана пустая ссылка");
        }
        matrixArray = Arrays.copyOf(matrix.matrixArray, matrix.matrixArray.length);
    }

    public Matrix(double[][] doubleArray) {
        if (doubleArray.length == 0) {
            throw new IndexOutOfBoundsException("Передан пустой массив.");
        }

        matrixArray = new Vector[doubleArray.length];
        int maxVectorLength = doubleArray[0].length;

        for (int i = 1; i < doubleArray.length; i++) {
            maxVectorLength = Math.max(maxVectorLength, doubleArray[i].length);
        }

        for (int i = 0; i < doubleArray.length; i++) {
            if (doubleArray[i].length < maxVectorLength) {
                double[] temp = new double[maxVectorLength];
                System.arraycopy(doubleArray[i], 0, temp, 0, doubleArray[i].length);
                for (int j = doubleArray[i].length; j < maxVectorLength; j++) {
                    temp[j] = 0;
                }
                matrixArray[i] = new Vector(temp);
            } else {
                matrixArray[i] = new Vector(doubleArray[i]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IndexOutOfBoundsException("Передан пустой массив.");
        }
        matrixArray = vectors;
    }

    public String getSize() {
        return matrixArray.length + "x" + matrixArray[0].getSize();
    }

    public Vector getVector(int n) {
        if (n <= 0 || n >= matrixArray.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        return matrixArray[n];
    }

    public void setVector(int n, Vector vector) {
        if (n <= 0 || n >= matrixArray.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        matrixArray[n] = vector;
    }

    public void transpose() {
        Matrix tempMatrix = new Matrix(matrixArray[0].getSize(), matrixArray.length);
        for (int i = 0; i < matrixArray[0].getSize(); i++) {
            for (int j = 0; j < matrixArray.length; j++) {
                tempMatrix.matrixArray[i].setVectorComponent(j, matrixArray[j].getVectorComponent(i));
            }
        }
        matrixArray = tempMatrix.matrixArray;
    }

    public void multiplication(int n) {
        for (Vector aMatrixArray : matrixArray) {
            aMatrixArray.multiplication(n);
        }
    }

    private void swapTwoStringsInMatrix(Vector[] matrixArray, int firstRow, int secondRow) {
        Vector temp = matrixArray[firstRow];
        matrixArray[firstRow] = matrixArray[secondRow];
        matrixArray[secondRow] = temp;
    }

    //взято с предыдущей сданной задачи и переделано
    public double determination() {
        if (matrixArray.length != matrixArray[0].getSize()) {
            throw new IllegalArgumentException("Только для квадратных матриц. Текущий размер матрицы:" + this.getSize());
        }

        double epsilon = 1E-5;
        int swapStringsCount = 0;

        Matrix tempMatrix = new Matrix(matrixArray.length, matrixArray.length);

        for (int i = 0; i < matrixArray.length; i++) {
            tempMatrix.matrixArray[i] = new Vector(matrixArray[i]);
        }

        for (int i = 0; i < tempMatrix.matrixArray.length - 1; i++) {
            for (int j = i + 1; j < tempMatrix.matrixArray.length; j++) {
                if (Math.abs(tempMatrix.matrixArray[i].getVectorComponent(i)) < epsilon) {
                    for (int p = j; p < tempMatrix.matrixArray.length; p++) {
                        if (Math.abs(tempMatrix.matrixArray[p].getVectorComponent(i)) >= epsilon) {
                            swapTwoStringsInMatrix(tempMatrix.matrixArray, i, p);
                            swapStringsCount++;
                            break;
                        }
                    }
                }

                if (Math.abs(tempMatrix.matrixArray[j].getVectorComponent(i)) < epsilon) {
                    continue;
                }

                double multiplier = tempMatrix.matrixArray[j].getVectorComponent(i) / tempMatrix.matrixArray[i].getVectorComponent(i);
                for (int k = i; k < tempMatrix.matrixArray.length; k++) {
                    tempMatrix.matrixArray[j].setVectorComponent(k, tempMatrix.matrixArray[j].getVectorComponent(k) - tempMatrix.matrixArray[i].getVectorComponent(k) * multiplier);
                }
            }
        }

        double determinant = 1;
        for (int i = 0; i < tempMatrix.matrixArray.length; i++) {
            determinant *= tempMatrix.matrixArray[i].getVectorComponent(i);
        }

        return Math.abs((swapStringsCount % 2 == 0 ? 1 : -1) * determinant);
    }

    public void sum(Matrix matrix) {
        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[0].getSize(); j++) {
                matrixArray[i].setVectorComponent(j, matrixArray[i].getVectorComponent(j) + matrix.matrixArray[i].getVectorComponent(j));
            }
        }
    }

    public void difference(Matrix matrix) {
        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[0].getSize(); j++) {
                matrixArray[i].setVectorComponent(j, matrixArray[i].getVectorComponent(j) - matrix.matrixArray[i].getVectorComponent(j));
            }
        }
    }

    public static Matrix MatrixSum(Matrix matrix1,Matrix matrix2){
        if (matrix1.matrixArray.length != matrix2.matrixArray.length || matrix1.matrixArray[0].getSize() != matrix2.matrixArray[0].getSize()){
            throw new IllegalArgumentException("Матрицы не равны по размеру.");
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixArray.length,matrix1.matrixArray[0].getSize());

        for (int i = 0; i < matrix1.matrixArray.length;i++){
            Vector tempVector = new Vector(matrix1.matrixArray[i]);
            tempVector.sum(matrix2.matrixArray[i]);
            resultMatrix.matrixArray[i] = new Vector(tempVector);
        }
        return resultMatrix;
    }

    public static Matrix MatrixDiss(Matrix matrix1,Matrix matrix2){
        if (matrix1.matrixArray.length != matrix2.matrixArray.length || matrix1.matrixArray[0].getSize() != matrix2.matrixArray[0].getSize()){
            throw new IllegalArgumentException("Матрицы не равны по размеру.");
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixArray.length,matrix1.matrixArray[0].getSize());

        for (int i = 0; i < matrix1.matrixArray.length;i++){
            Vector tempVector = new Vector(matrix1.matrixArray[i]);
            tempVector.difference(matrix2.matrixArray[i]);
            resultMatrix.matrixArray[i] = new Vector(tempVector);
        }
        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (Vector aMatrixArray : matrixArray) {
            result.append(aMatrixArray.toString()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        result.append("}");
        return String.valueOf(result);
    }
}
