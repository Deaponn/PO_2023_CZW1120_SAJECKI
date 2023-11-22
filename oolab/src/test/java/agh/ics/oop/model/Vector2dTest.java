package test.java.agh.ics.oop.model;

import main.java.agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        assertEquals("(1,2)", new Vector2d(1, 2).toString());
        assertEquals("(-1,2)", new Vector2d(-1, 2).toString());
        assertEquals("(1,-2)", new Vector2d(1, -2).toString());
        assertEquals("(-1,-2)", new Vector2d(-1, -2).toString());
        assertEquals("(100,2000)", new Vector2d(100, 2000).toString());
    }

    @Test
    void precedes() {
        Vector2d upperRight = new Vector2d(10, 13);
        Vector2d upperLeft = new Vector2d(-10, 13);
        Vector2d lowerRight = new Vector2d(10, -3);
        Vector2d lowerLeft = new Vector2d(-10_000, -30);
        Vector2d middle = new Vector2d(5, 7);

        // lowerLeft does precede every other Vector
        assertAll("Test lowerLeft against every other Vector",
                () -> assertTrue(lowerLeft.precedes(upperRight)),
                () -> assertTrue(lowerLeft.precedes(upperLeft)),
                () -> assertTrue(lowerLeft.precedes(lowerRight)),
                () -> assertTrue(lowerLeft.precedes(lowerLeft)),
                () -> assertTrue(lowerLeft.precedes(middle))
        );

        assertAll("Test upperLeft against every other Vector",
                // upperLeft does not precede some of the Vectors
                () -> assertFalse(upperLeft.precedes(lowerRight)),
                () -> assertFalse(upperLeft.precedes(lowerLeft)),
                () -> assertFalse(upperLeft.precedes(middle)),
                // upperLeft does precede some of the Vectors
                () -> assertTrue(upperLeft.precedes(upperRight)),
                () -> assertTrue(upperLeft.precedes(upperLeft))
        );

        assertAll("Test middle against every other Vector",
                // middle does not precede some of the Vectors
                () -> assertFalse(middle.precedes(lowerRight)),
                () -> assertFalse(middle.precedes(upperLeft)),
                () -> assertFalse(middle.precedes(lowerLeft)),
                // middle does precede some of the Vectors
                () -> assertTrue(middle.precedes(upperRight)),
                () -> assertTrue(middle.precedes(middle))
        );
    }

    @Test
    void follows() {
        Vector2d upperRight = new Vector2d(9000, 130);
        Vector2d upperLeft = new Vector2d(-9, 13);
        Vector2d lowerRight = new Vector2d(9, -3);
        Vector2d lowerLeft = new Vector2d(-9, -3);
        Vector2d middle = new Vector2d(4, 7);

        assertAll("Test lowerLeft against every other Vector",
                // lowerLeft does not follow some Vectors
                () -> assertFalse(lowerLeft.follows(upperRight)),
                () -> assertFalse(lowerLeft.follows(upperLeft)),
                () -> assertFalse(lowerLeft.follows(lowerRight)),
                () -> assertFalse(lowerLeft.follows(middle)),
                // lowerLeft does follow only one Vector
                () -> assertTrue(lowerLeft.follows(lowerLeft))
        );

        assertAll("Test upperLeft against every other Vector",
                // upperLeft does not follow some of the Vectors
                () -> assertFalse(upperLeft.follows(upperRight)),
                () -> assertFalse(upperLeft.follows(lowerRight)),
                () -> assertFalse(upperLeft.follows(middle)),
                // upperLeft does follow some of the Vectors
                () -> assertTrue(upperLeft.follows(upperLeft)),
                () -> assertTrue(upperLeft.follows(lowerLeft))
        );

        assertAll("Test middle against every other Vector",
                // middle does not follow some of the Vectors
                () -> assertFalse(middle.follows(upperRight)),
                () -> assertFalse(middle.follows(lowerRight)),
                () -> assertFalse(middle.follows(upperLeft)),
                // middle does follow some of the Vectors
                () -> assertTrue(middle.follows(lowerLeft)),
                () -> assertTrue(middle.follows(middle))
        );
    }

    @Test
    void add() {
        Vector2d vector_1 = new Vector2d(40_000, 120);
        Vector2d vector_2 = new Vector2d(9, -300_000);
        Vector2d vector_3 = new Vector2d(-0, 0);
        Vector2d vector_4 = new Vector2d(-20, 5);

        assertAll("Test adding vectors",
                () -> assertEquals(new Vector2d(40_009, -299_880), vector_1.add(vector_2)),
                () -> assertEquals(new Vector2d(9, -300_000), vector_2.add(vector_3)),
                () -> assertEquals(new Vector2d(-20, 5), vector_3.add(vector_4)),
                () -> assertEquals(new Vector2d(39_980, 125), vector_4.add(vector_1))
        );

        assertAll("Test cross-adding vectors",
                () -> assertEquals(vector_1.add(vector_3), vector_3.add(vector_1)),
                () -> assertEquals(vector_2.add(vector_3), vector_3.add(vector_2)),
                () -> assertEquals(vector_2.add(vector_4), vector_4.add(vector_2)),
                () -> assertEquals(vector_3.add(vector_3), vector_3.add(vector_3)),
                () -> assertEquals(vector_3.add(vector_4), vector_4.add(vector_3))
        );
    }

    @Test
    void subtract() {
        Vector2d vector_1 = new Vector2d(40_009, 120);
        Vector2d vector_2 = new Vector2d(9, -20_000);
        Vector2d vector_3 = new Vector2d(0, -0);
        Vector2d vector_4 = new Vector2d(-2, 50);

        assertAll("Test subtracting vectors",
                () -> assertEquals(new Vector2d(40_000, 20_120), vector_1.subtract(vector_2)),
                () -> assertEquals(new Vector2d(9, -20_000), vector_2.subtract(vector_3)),
                () -> assertEquals(new Vector2d(-40_009, -120), vector_3.subtract(vector_1)),
                () -> assertEquals(new Vector2d(-9, 20_000), vector_3.subtract(vector_2)),
                () -> assertEquals(new Vector2d(2, -50), vector_3.subtract(vector_4)),
                () -> assertEquals(new Vector2d(-40_011, -70), vector_4.subtract(vector_1)),
                () -> assertEquals(new Vector2d(-11, 20_050), vector_4.subtract(vector_2)),
                () -> assertEquals(new Vector2d(-2, 50), vector_4.subtract(vector_3))
        );

        assertAll("Test subtracting vector from itself",
                () -> assertEquals(new Vector2d(0, 0), vector_1.subtract(vector_1)),
                () -> assertEquals(new Vector2d(0, 0), vector_2.subtract(vector_2)),
                () -> assertEquals(new Vector2d(0, 0), vector_3.subtract(vector_3)),
                () -> assertEquals(new Vector2d(0, 0), vector_4.subtract(vector_4))
        );
    }

    @Test
    void upperRight() {
        Vector2d vector_1 = new Vector2d(9000, 140);
        Vector2d vector_2 = new Vector2d(-9, 140);
        Vector2d vector_3 = new Vector2d(96, -6);
        Vector2d vector_4 = new Vector2d(-32, -3);
        Vector2d vector_5 = new Vector2d(4, 7);

        assertAll("Test finding upperRight corner of a rectangle",
                () -> assertEquals(new Vector2d(9000, 140), vector_1.upperRight(vector_2)),
                () -> assertEquals(new Vector2d(9000, 140), vector_1.upperRight(vector_3)),
                () -> assertEquals(new Vector2d(9000, 140), vector_1.upperRight(vector_4)),
                () -> assertEquals(new Vector2d(9000, 140), vector_1.upperRight(vector_5)),
                () -> assertEquals(new Vector2d(-9, 140), vector_2.upperRight(vector_4)),
                () -> assertEquals(new Vector2d(96, -3), vector_3.upperRight(vector_4)),
                () -> assertEquals(new Vector2d(4, 7), vector_4.upperRight(vector_5)),
                () -> assertEquals(new Vector2d(4, 140), vector_5.upperRight(vector_2)),
                () -> assertEquals(new Vector2d(96, 7), vector_5.upperRight(vector_3))
        );

        assertAll("Test vectors against each other",
                () -> assertEquals(vector_2.upperRight(vector_1), vector_1.upperRight(vector_2)),
                () -> assertEquals(vector_2.upperRight(vector_5), vector_5.upperRight(vector_2)),
                () -> assertEquals(vector_3.upperRight(vector_1), vector_1.upperRight(vector_3)),
                () -> assertEquals(vector_3.upperRight(vector_5), vector_5.upperRight(vector_3)),
                () -> assertEquals(vector_4.upperRight(vector_1), vector_1.upperRight(vector_4)),
                () -> assertEquals(vector_4.upperRight(vector_2), vector_2.upperRight(vector_4)),
                () -> assertEquals(vector_4.upperRight(vector_3), vector_3.upperRight(vector_4)),
                () -> assertEquals(vector_5.upperRight(vector_1), vector_1.upperRight(vector_5)),
                () -> assertEquals(vector_5.upperRight(vector_4), vector_4.upperRight(vector_5))
        );
    }

    @Test
    void lowerLeft() {
        Vector2d vector_1 = new Vector2d(9000, 145);
        Vector2d vector_2 = new Vector2d(-9, 135);
        Vector2d vector_3 = new Vector2d(96, -5);
        Vector2d vector_4 = new Vector2d(-36, -3);
        Vector2d vector_5 = new Vector2d(1, 7);

        assertAll("Test finding lowerLeft corner of a rectangle",
                () -> assertEquals(new Vector2d(-9, 135), vector_1.lowerLeft(vector_2)),
                () -> assertEquals(new Vector2d(96, -5), vector_1.lowerLeft(vector_3)),
                () -> assertEquals(new Vector2d(-36, -3), vector_1.lowerLeft(vector_4)),
                () -> assertEquals(new Vector2d(1, 7), vector_1.lowerLeft(vector_5)),
                () -> assertEquals(new Vector2d(-36, -3), vector_2.lowerLeft(vector_4)),
                () -> assertEquals(new Vector2d(-36, -5), vector_3.lowerLeft(vector_4)),
                () -> assertEquals(new Vector2d(-36, -3), vector_4.lowerLeft(vector_5)),
                () -> assertEquals(new Vector2d(-9, 7), vector_5.lowerLeft(vector_2)),
                () -> assertEquals(new Vector2d(1, -5), vector_5.lowerLeft(vector_3))
        );

        assertAll("Test vectors against each other",
                () -> assertEquals(vector_2.lowerLeft(vector_1), vector_1.lowerLeft(vector_2)),
                () -> assertEquals(vector_2.lowerLeft(vector_5), vector_5.lowerLeft(vector_2)),
                () -> assertEquals(vector_3.lowerLeft(vector_1), vector_1.lowerLeft(vector_3)),
                () -> assertEquals(vector_3.lowerLeft(vector_5), vector_5.lowerLeft(vector_3)),
                () -> assertEquals(vector_4.lowerLeft(vector_1), vector_1.lowerLeft(vector_4)),
                () -> assertEquals(vector_4.lowerLeft(vector_2), vector_2.lowerLeft(vector_4)),
                () -> assertEquals(vector_4.lowerLeft(vector_3), vector_3.lowerLeft(vector_4)),
                () -> assertEquals(vector_5.lowerLeft(vector_1), vector_1.lowerLeft(vector_5)),
                () -> assertEquals(vector_5.lowerLeft(vector_4), vector_4.lowerLeft(vector_5))
        );
    }

    @Test
    void opposite() {
        Vector2d vector_1 = new Vector2d(8000, 145);
        Vector2d vector_2 = new Vector2d(-20, 135);
        Vector2d vector_3 = new Vector2d(96, -1);
        Vector2d vector_4 = new Vector2d(-16, -3);
        Vector2d vector_5 = new Vector2d(0, 7);

        assertAll("Test finding opposite Vector",
                () -> assertEquals(new Vector2d(-8000, -145), vector_1.opposite()),
                () -> assertEquals(new Vector2d(20, -135), vector_2.opposite()),
                () -> assertEquals(new Vector2d(-96, 1), vector_3.opposite()),
                () -> assertEquals(new Vector2d(16, 3), vector_4.opposite()),
                () -> assertEquals(new Vector2d(-0, -7), vector_5.opposite())
        );

        assertAll("Test finding opposite Vector twice",
                () -> assertEquals(vector_1, vector_1.opposite().opposite()),
                () -> assertEquals(vector_2, vector_2.opposite().opposite()),
                () -> assertEquals(vector_3, vector_3.opposite().opposite()),
                () -> assertEquals(vector_4, vector_4.opposite().opposite()),
                () -> assertEquals(vector_5, vector_5.opposite().opposite())
        );
    }

    @Test
    void testEquals() {
        Vector2d vector_1 = new Vector2d(10, 10);
        Vector2d vector_2 = new Vector2d(-10_000, 10);
        Vector2d vector_3 = new Vector2d(10, -10);

        assertAll("Test equality of a Vector",
                () -> assertTrue(Objects.equals(vector_1, vector_1)),
                () -> assertTrue(Objects.equals(vector_2, vector_2)),
                () -> assertTrue(Objects.equals(vector_3, vector_3)),
                () -> assertTrue(Objects.equals(new Vector2d(10, 10), vector_1)),
                () -> assertTrue(Objects.equals(new Vector2d(-10_000, 10), vector_2)),
                () -> assertTrue(Objects.equals(vector_3, new Vector2d(10, -10)))
        );

        assertAll("Test inequality of a Vector",
                () -> assertFalse(Objects.equals(vector_1, vector_2)),
                () -> assertFalse(Objects.equals(vector_2, vector_3)),
                () -> assertFalse(Objects.equals(vector_3, vector_1)),
                () -> assertFalse(Objects.equals(new Vector2d(10, 10), "test string")),
                () -> assertFalse(Objects.equals(new Vector2d(-10_000, 10), null)),
                () -> assertFalse(Objects.equals(10_000, new Vector2d(10, -10)))
        );
    }
}