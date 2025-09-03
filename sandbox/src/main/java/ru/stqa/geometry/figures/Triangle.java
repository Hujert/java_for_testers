package ru.stqa.geometry.figures;

public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        if (!areSidesPositive(sideA, sideB, sideC)) {
            throw new IllegalArgumentException("Одна из сторон имеет отрицательное значение");
        }
        if (!satisfiesTriangleInequality(sideA, sideB, sideC)) {
            throw new IllegalArgumentException("Стороны не образуют треугольник");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
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

    public static boolean satisfiesTriangleInequality(double a, double b, double c) {
        return a + b > c &&
               a + c > b &&
               b + c > a;
    }

    public static boolean areSidesPositive(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0;
    }
}
