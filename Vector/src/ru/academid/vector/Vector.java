package ru.academid.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            System.out.println("Некорректное значение size: " + size);
            throw new IllegalArgumentException();
        }
        components = new double[size];
    }

    public Vector(double[] vectorArray) {
        if (vectorArray.length == 0) {
            System.out.println("Передан пустой массив.");
            throw new IllegalArgumentException();
        }
        components = Arrays.copyOf(vectorArray, vectorArray.length);
    }

    public Vector(int size, double[] vectorComponents) {
        if (size <= 0) {
            System.out.println("Некорректное значение size: " + size);
            throw new IllegalArgumentException();
        }
        if (vectorComponents.length == 0) {
            System.out.println("Передан пустой массив.");
            throw new IllegalArgumentException();
        }
        components = Arrays.copyOf(vectorComponents, size);
    }

    public Vector(Vector vectorFromCopy) {
        if (vectorFromCopy == null) {
            System.out.println("Передан пустой вектор.");
            throw new IllegalArgumentException();
        }
        components = Arrays.copyOf(vectorFromCopy.components, vectorFromCopy.components.length);
    }

    public double getVectorComponent(int number) {
        if (number < 0 || number >= components.length) {
            System.out.println("Компоненты с номером " + number + " не существует у вектора: " + toString());
            throw new IndexOutOfBoundsException();
        }
        return components[number];
    }

    public void setVectorComponent(int number, double value) {
        if (number < 0 || number >= components.length) {
            System.out.println("Компоненты с номером " + number + " не существует у вектора: " + toString());
            throw new IndexOutOfBoundsException();
        }
        components[number] = value;
    }

    public void sum(Vector vector) {
        if (components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; i++) {
                setVectorComponent(i, getVectorComponent(i) + vector.getVectorComponent(i));
            }
        } else {
            for (int i = 0; i < components.length; i++) {
                vector.setVectorComponent(i, vector.getVectorComponent(i) + getVectorComponent(i));
            }
            components = Arrays.copyOf(vector.components, vector.components.length);
        }
    }

    public void difference(Vector vector) {
        if (components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; i++) {
                setVectorComponent(i, getVectorComponent(i) - vector.getVectorComponent(i));
            }
        } else {
            for (int i = 0; i < components.length; i++) {
                vector.setVectorComponent(i, vector.getVectorComponent(i) - getVectorComponent(i));
            }
            components = Arrays.copyOf(vector.components, vector.components.length);
        }
    }

    public int getSize() {
        return components.length;
    }

    public void multiplication(double n) {
        if (n == 0) {
            System.out.println("Множитель равен нулю.");
            throw new IllegalArgumentException();
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
        boolean isVector1 = vector1.components.length >= vector2.components.length;

        Vector resultVector = new Vector(isVector1 ? vector1 : vector2);
        resultVector.sum(isVector1 ? vector2 : vector1);

        return resultVector;
    }

    public static Vector vectorsDifference(Vector vector1, Vector vector2) {
        boolean isVector1 = vector1.components.length >= vector2.components.length;

        Vector resultVector = new Vector(isVector1 ? vector1 : vector2);
        resultVector.difference(isVector1 ? vector2 : vector1);

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
        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < components.length; i++) {
            resultString.append(String.format(" %.1f ", getVectorComponent(i))).append(i == components.length - 1 ? "" : ";");
        }

        return "{" + resultString + "}";
    }

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
