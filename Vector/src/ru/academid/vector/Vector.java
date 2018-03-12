package ru.academid.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int size;
    private double[] vectorComponents;

    public Vector(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.vectorComponents = new double[size];
    }

    public Vector(double[] vectorArray) {
        if (vectorArray.length == 0) {
            throw new IllegalArgumentException();
        }
        this.size = vectorArray.length;
        this.vectorComponents = vectorArray;
    }

    public Vector(int size, double[] vectorComponents) {
        if (size < 0 || vectorComponents.length > size || vectorComponents.length == 0) {
            throw new IllegalArgumentException();
        }

        this.size = size;
        this.vectorComponents = new double[size];

        int length = vectorComponents.length;

        for (int i = 0; i < size; i++) {
            if (i < length) {
                this.vectorComponents[i] = vectorComponents[i];
            } else {
                this.vectorComponents[i] = 0;
            }
        }
    }

    public Vector(Vector vectorFromCopy) {
        this.size = vectorFromCopy.size;
        this.vectorComponents = Arrays.copyOf(vectorFromCopy.vectorComponents, vectorFromCopy.vectorComponents.length);
    }

    public int getSize() {
        return size;
    }

    public double getVectorComponent(int number) {
        if (number < 0 || number >= this.getSize()) {
            throw new IllegalArgumentException();
        }
        return vectorComponents[number];
    }

    public void setVectorComponent(int number, double value) {
        if (number < 0 || number >= this.getSize()) {
            throw new IllegalArgumentException();
        }
        this.vectorComponents[number] = value;
    }

    public void sum(Vector vector) {
        if (vector.getSize() > this.getSize()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < vector.getSize(); i++) {
            this.setVectorComponent(i, this.getVectorComponent(i) + vector.getVectorComponent(i));
        }
    }

    public void difference(Vector vector) {
        if (vector.getSize() > this.getSize()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < vector.getSize(); i++) {
            this.setVectorComponent(i, this.getVectorComponent(i) - vector.getVectorComponent(i));
        }
    }

    public void multiplication(double n) {
        if (n == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < this.getSize(); i++) {
            this.setVectorComponent(i, this.getVectorComponent(i) * n);
        }
    }

    public void spin() {
        for (int i = 0; i < this.getSize(); i++) {
            this.multiplication(-1);
        }
    }

    public double length() {
        double sum = 0;
        for (int i = 0; i < this.getSize(); i++) {
            sum += Math.pow(this.getVectorComponent(i), 2);
        }
        return Math.sqrt(sum);
    }

    public static Vector vectorsSum(Vector vector1, Vector vector2) {
        boolean isVector1 = vector1.getSize() >= vector2.getSize();

        Vector resultVector = new Vector(isVector1 ? vector1 : vector2);
        resultVector.sum(isVector1 ? vector2 : vector1);

        return resultVector;
    }

    public static Vector vectorsDifference(Vector vector1, Vector vector2) {
        boolean isVector1 = vector1.getSize() >= vector2.getSize();

        Vector resultVector = new Vector(isVector1 ? vector1 : vector2);
        resultVector.difference(isVector1 ? vector2 : vector1);

        return resultVector;
    }

    public static double vectorsScalarDifference(Vector vector1, Vector vector2) {
        double resultDifference = 0;

        boolean isVector1 = vector1.getSize() >= vector2.getSize();
        int sizeOfLessVector = isVector1 ? vector2.getSize() : vector1.getSize();

        for (int i = 0; i < sizeOfLessVector; i++) {
            resultDifference += vector1.getVectorComponent(i) * vector2.getVectorComponent(i);
        }
        return resultDifference;
    }

    @Override
    public String toString() {
        String resultString = "";
        for (int i = 0; i < this.getSize(); i++) {
            resultString = resultString.concat(String.format(" %.1f ", this.getVectorComponent(i)) + (i == this.getSize() - 1 ? "" : ";"));
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
        return size == vector.size &&
                Arrays.equals(vectorComponents, vector.vectorComponents);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(vectorComponents);
        return result;
    }
}
