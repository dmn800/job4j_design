package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenNumberOfVertices8() {
        Box box = new Box(8, 5);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8)
                .isGreaterThan(7);
    }

    @Test
    void whenNumberOfVerticesMinus1() {
        Box box = new Box(-5, 5);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1)
                .isLessThan(0);
    }

    @Test
    void whenExistIsTrue() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenExistIsFalse() {
        Box box = new Box(3, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenAreaOfSphereIs1256D64() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.63d, withPrecision(0.01d))
                .isCloseTo(1256.6, withPrecision(0.1d));
    }

    @Test
    void whenAreaOfCubeIs24() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24)
                .isLessThan(25);
    }
}