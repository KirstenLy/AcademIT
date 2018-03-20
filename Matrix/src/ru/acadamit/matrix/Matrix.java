package ru.acadamit.matrix;

import ru.academit.vector.Vector;

public class Matrix {
    private Vector[] matrixArray;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Некорректное значение n: " + n);
        }
        if (m <= 0) {
            throw new IllegalArgumentException("Некорректное значение m: " + m);
        }

        matrixArray = new Vector[n];

        for (int i = 0; i < n; i++) {
            matrixArray[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Передан null.");
        }

        matrixArray = new Vector[matrix.matrixArray.length];

        for (int i = 0; i < matrix.matrixArray.length; i++) {
            matrixArray[i] = new Vector(matrix.getVector(i));
        }
    }

    public Matrix(double[][] doubleArray) {
        if (doubleArray.length == 0) {
            throw new IndexOutOfBoundsException("Передан пустой массив.");
        }

        int vectorsLength = 0;
        int emptyVectorsCounter = 0;
        int corrector = 0;

        for (double[] aDoubleArray : doubleArray) {
            vectorsLength = Math.max(vectorsLength, aDoubleArray.length);
            if (aDoubleArray.length == 0) {
                emptyVectorsCounter++;
            }
        }

        matrixArray = new Vector[doubleArray.length - emptyVectorsCounter];

        for (int i = 0; i < doubleArray.length;i++){
            if (doubleArray[i].length == 0) {
                corrector++;
                continue;
            }
            matrixArray[i - corrector] = new Vector(vectorsLength,doubleArray[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Передан null.");
        }
        if (vectors.length == 0) {
            throw new IndexOutOfBoundsException("Передан пустой массив.");
        }
        Vector[] tempArray = new Vector[vectors.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = new Vector(vectors[i]);
        }
        matrixArray = tempArray;
    }

    public String getSize() {
        return matrixArray.length + "x" + matrixArray[0].getSize();
    }

    public Vector getVector(int n) {
        if (n < 0 || n >= matrixArray.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        return matrixArray[n];
    }

    public void setVector(int n, Vector vector) {
        if (n < 0 || n >= matrixArray.length) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        matrixArray[n] = new Vector(vector);
    }

    public void transpose() {
        Matrix tempMatrix = new Matrix(matrixArray[0].getSize(), matrixArray.length);
        for (int i = 0; i < matrixArray[0].getSize(); i++) {
            tempMatrix.matrixArray[i] = new Vector(getVectorFromRow(i));
        }
        matrixArray = tempMatrix.matrixArray;
    }

    public void multiplication(int n) {
        for (Vector aMatrixArray : matrixArray) {
            aMatrixArray.multiplication(n);
        }
    }

    //взято с предыдущей сданной задачи и подкорректировано
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
        if (matrix == null) {
            throw new NullPointerException("Передан null.");
        }
        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[0].getSize(); j++) {
                matrixArray[i].setVectorComponent(j, matrixArray[i].getVectorComponent(j) + matrix.matrixArray[i].getVectorComponent(j));
            }
        }
    }

    public void difference(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Передан null.");
        }
        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[0].getSize(); j++) {
                matrixArray[i].setVectorComponent(j, matrixArray[i].getVectorComponent(j) - matrix.matrixArray[i].getVectorComponent(j));
            }
        }
    }

    public static Matrix MatrixSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("Передан null.");
        }
        if (matrix1.matrixArray.length != matrix2.matrixArray.length ||
                matrix1.matrixArray[0].getSize() != matrix2.matrixArray[0].getSize()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getSize() + "," + matrix2.getSize());
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixArray.length, matrix1.matrixArray[0].getSize());

        for (int i = 0; i < matrix1.matrixArray.length; i++) {
            resultMatrix.matrixArray[i] = new Vector(matrix1.getVector(i));
            resultMatrix.matrixArray[i].sum(matrix2.getVector(i));
        }
        return resultMatrix;
    }

    public static Matrix MatrixDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("Передан null.");
        }
        if (matrix1.matrixArray.length != matrix2.matrixArray.length ||
                matrix1.matrixArray[0].getSize() != matrix2.matrixArray[0].getSize()) {
            throw new IllegalArgumentException("Размерности матриц не равны: " + matrix1.getSize() + "," + matrix2.getSize());
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixArray.length, matrix1.matrixArray[0].getSize());

        for (int i = 0; i < matrix1.matrixArray.length; i++) {
            resultMatrix.matrixArray[i] = new Vector(matrix1.getVector(i));
            resultMatrix.matrixArray[i].difference(matrix2.getVector(i));
        }
        return resultMatrix;
    }

    public static Matrix MatrixDetermination(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("Передан null.");
        }
        if (matrix1.matrixArray.length != matrix2.matrixArray[0].getSize()) {
            throw new IndexOutOfBoundsException("Число столбцов первого сомножителя не равно числу строк второго сомножителя.");
        }

        Matrix resultMatrix = new Matrix(matrix1.matrixArray.length, matrix1.matrixArray[0].getSize());

        double temp = 0;
        Vector tempVector1;
        Vector tempVector2;

        for (int i = 0; i < resultMatrix.matrixArray.length; i++) {
            tempVector1 = matrix1.getVector(i);
            for (int j = 0; j < resultMatrix.matrixArray[0].getSize(); j++) {
                tempVector2 = matrix2.getVectorFromRow(j);
                for (int k = 0; k < resultMatrix.matrixArray[0].getSize(); k++) {
                    temp += tempVector1.getVectorComponent(k) * tempVector2.getVectorComponent(k);
                }
                resultMatrix.matrixArray[i].setVectorComponent(j, temp);
                temp = 0;
            }
        }
        return resultMatrix;
    }

    public static Vector MatrixDeterminationOnVector(Matrix matrix, Vector vector) {
        if (matrix == null || vector == null) {
            throw new NullPointerException("Передан null");
        }
        Vector tempVector = new Vector(matrix.getVector(0).getSize());

        for (int i = 0; i < matrix.getVectorFromRow(0).getSize(); i++) {
            for (int j = 0; j < matrix.getVector(0).getSize(); j++) {
                tempVector.setVectorComponent(i, tempVector.getVectorComponent(i) + matrix.matrixArray[i].getVectorComponent(j) * vector.getVectorComponent(j));
            }
        }
        return tempVector;
    }

    private Vector getVectorFromRow(int n) {
        if (n < 0 || n >= matrixArray[0].getSize()) {
            throw new IndexOutOfBoundsException("Вектора с номером: " + n + " не существует.");
        }
        double[] temp = new double[matrixArray.length];

        for (int i = 0; i < matrixArray.length; i++) {
            temp[i] = matrixArray[i].getVectorComponent(n);
        }
        return new Vector(temp);
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
        for (Vector aMatrixArray : matrixArray) {
            result.append(aMatrixArray.toString()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        result.append("}");
        return String.valueOf(result);
    }
}
