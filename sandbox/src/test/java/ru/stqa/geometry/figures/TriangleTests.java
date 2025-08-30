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
        Assertions.assertEquals(6.0, new Triangle(3.0, 4.0, 5.0).semiperimeter());
    }
}
