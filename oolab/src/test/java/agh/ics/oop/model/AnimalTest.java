package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void isAt() {
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(4, 10));
        assertTrue(animal1.isAt(new Vector2d(2, 2)));
        assertFalse(animal1.isAt(new Vector2d(4, 10)));
        assertTrue(animal2.isAt(new Vector2d(4, 10)));
    }

    @Test
    void move() {
        Animal animal = new Animal();

        // test going forward, backward and upper boundary
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 3)));
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2, 2)));
        for (int i = 0; i < 100; i++) animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 4)));

        // test turning left and left boundary
        animal.move(MoveDirection.TURN_LEFT);
        assertTrue(animal.isAt(new Vector2d(2, 4)));
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(1, 4)));
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2, 4)));
        for (int i = 0; i < 100; i++) animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(0, 4)));

        // test turning right and right boundary
        animal.move(MoveDirection.TURN_RIGHT);
        animal.move(MoveDirection.TURN_RIGHT);
        assertTrue(animal.isAt(new Vector2d(0, 4)));
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(1, 4)));
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(0, 4)));
        for (int i = 0; i < 100; i++) animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(4, 4)));

        // test backward lower boundary
        animal.move(MoveDirection.TURN_LEFT);
        assertTrue(animal.isAt(new Vector2d(4, 4)));
        for (int i = 0; i < 100; i++) animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(4, 0)));
    }
}