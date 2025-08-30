package ru.stqa.geometry.figures;

public record Triangle(double sideA, double sideB, double sideC ) {

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
}
