package ru.stqa.geometry.figures;

public class Triangle {
    public static void printTriangleAria(double a, double b, double c) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", a, b, c, area(a, b, c));
        System.out.println(text);
    }

    public static double area(double a, double b, double c) {
        double p = semiperimeter(a, b, c);
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static double semiperimeter(double a, double b, double c) {
        return (a + b + c) / 2;
        }
}
