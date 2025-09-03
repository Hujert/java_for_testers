package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var s = new Triangle(3.0, 4.0, 5.0);
        double resalt = s.area();
        Assertions.assertEquals(6, resalt);
    }

    @Test
    void canCalculateSemiperimeter() {
        var p = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(6.0, p.semiperimeter());
    }

    @Test
    void triangleInequalityTest() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(15, 4, 7)
        );
        Assertions.assertTrue(exception.getMessage().contains("Стороны не образуют треугольник"));
    }

    @Test
    void negativeSideTest() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(1, 4, -1)
        );
        Assertions.assertTrue(exception.getMessage().contains("Одна из сторон имеет отрицательное значение"));
    }

    @Test
    void testEquality() {
        var p = new Triangle(3.0, 4.0, 5.0);
        var p2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(p, p2);
    }

    @Test
    void testNonEquality() {
        var p = new Triangle(3.0, 4.0, 5.0);
        var p2 = new Triangle(4.0, 5.0, 6.0);
        Assertions.assertNotEquals(p, p2);
    }

    @Test
    void testPass() {
        var p = new Triangle(3.0, 4.0, 5.0);
        var p2 = new Triangle(5.0, 3.0, 4.0);
        Assertions.assertTrue(p.equals(p2));
    }
}
