package ru.academid.vector;

import java.util.Arrays;

//1 2 3

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Некорректное значение size: " + size);
        }
        components = new double[size];
    }

    public Vector(double[] vectorArray) {
        if (vectorArray.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }
        components = Arrays.copyOf(vectorArray, vectorArray.length);
    }

    public Vector(int size, double[] vectorComponents) {
        if (size <= 0) {
            throw new IllegalArgumentException("Некорректное значение size: " + size);
        }
        if (vectorComponents.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }
        components = Arrays.copyOf(vectorComponents, size);
    }

    public Vector(Vector vectorFromCopy) {
        if (vectorFromCopy == null) {
            throw new IllegalArgumentException("Передан пустой вектор.");
        }
        components = Arrays.copyOf(vectorFromCopy.components, vectorFromCopy.components.length);
    }

    public double getVectorComponent(int number) {
        if (number < 0 || number >= components.length) {
            throw new IndexOutOfBoundsException("Компоненты с номером " + number + " не существует у вектора: " + toString());
        }
        return components[number];
    }

    public void setVectorComponent(int number, double value) {
        if (number < 0 || number >= components.length) {
            throw new IndexOutOfBoundsException("Компоненты с номером " + number + " не существует у вектора: " + toString());
        }
        components[number] = value;
    }

    public void sum(Vector vector) {
        if (components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; i++) {
                setVectorComponent(i, getVectorComponent(i) + vector.getVectorComponent(i));
            }
        } else {
            double[] sumComponents = new double[vector.components.length];

            System.arraycopy(components, 0, sumComponents, 0, components.length);
            System.arraycopy(vector.components, components.length, sumComponents, components.length, vector.components.length - components.length);

            for (int i = 0; i < components.length; i++) {
                sumComponents[i] = components[i] + vector.getVectorComponent(i);
            }

            components = Arrays.copyOf(sumComponents, sumComponents.length);
        }
    }

    public void difference(Vector vector) {
        if (components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; i++) {
                setVectorComponent(i, getVectorComponent(i) - vector.getVectorComponent(i));
            }
        } else {
            double[] sumComponents = new double[vector.components.length];

            System.arraycopy(components, 0, sumComponents, 0, components.length);
            System.arraycopy(vector.components, components.length, sumComponents, components.length, vector.components.length - components.length);

            for (int i = 0; i < components.length; i++) {
                sumComponents[i] = components[i] - vector.getVectorComponent(i);
            }

            components = Arrays.copyOf(sumComponents, sumComponents.length);
        }
    }

    public int getSize() {
        return components.length;
    }

    public void multiplication(double n) {
        if (n == 0) {
            throw new IllegalArgumentException("Множитель равен нулю.");
        }
        for (int i = 0; i < components.length; i++) {
            setVectorComponent(i, getVectorComponent(i) * n);
        }
    }

    public void spin() {
        multiplication(-1);
    }

    public double length() {
        double sum = 0;
        for (int i = 0; i < components.length; i++) {
            sum += Math.pow(getVectorComponent(i), 2);
        }
        return Math.sqrt(sum);
    }

    public static Vector vectorsSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.sum(vector2);
        return resultVector;
    }

    public static Vector vectorsDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.difference(vector2);
        return resultVector;
    }

    public static double vectorsScalarMultiplication(Vector vector1, Vector vector2) {
        double resultDifference = 0;

        int sizeOfLessVector = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < sizeOfLessVector; i++) {
            resultDifference += vector1.getVectorComponent(i) * vector2.getVectorComponent(i);
        }
        return resultDifference;
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }

    /* @Override
        public String toString() {
            StringBuilder resultString = new StringBuilder();

            for (int i = 0; i < components.length; i++) {
                resultString.append(String.format(" %.1f ", getVectorComponent(i))).append(i == components.length - 1 ? "" : ",");
            }

            return "{" + resultString + "}";
        }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return Arrays.equals(components, vector.components);
    }

}
