package ru.stqa.geometry.figures;

import java.util.Objects;

public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        if (!areSidesPositive()) {
            throw new IllegalArgumentException("Одна из сторон имеет отрицательное значение");
        }
        if (!satisfiesTriangleInequality()) {
            throw new IllegalArgumentException("Стороны не образуют треугольник");
        }
    }

    public static void printTriangleAria(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.sideA, t.sideB, t.sideC, t.area());
        System.out.println(text);
    }

    public double area() {
        double p = semiperimeter();
        return Math.sqrt(p * (p - this.sideA) * (p - this.sideB) * (p - this.sideC));
    }

    public double semiperimeter() {
        return (this.sideA + this.sideB + this.sideC) / 2;
        }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideC) == 0) ||
                (Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideB) == 0) ||
                (Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideC) == 0) ||
                (Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideA) == 0) ||
                (Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideB) == 0) ||
                (Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideA) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

    public boolean satisfiesTriangleInequality() {
        return this.sideA + this.sideB > this.sideC &&
                this.sideA + this.sideC > this.sideB &&
                this.sideB + this.sideC > this.sideA;
    }

    public boolean areSidesPositive() {
        return this.sideA > 0 && this.sideB > 0 && this.sideC > 0;
    }
}
