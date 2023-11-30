package agh.ics.oop.model;

import java.util.Objects;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public Animal() {
        this(new Vector2d(2, 2));
    }

    @Override
    public String toString() {
        return switch(orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public boolean isAt(Vector2d position) {
        return Objects.equals(position, this.position);
    }

    public Vector2d position() { return position; }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch(direction) {
            case TURN_RIGHT -> orientation = orientation.next();
            case TURN_LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                if (validator.canMoveTo(position.add(orientation.toUnitVector())))
                    position = position.add(orientation.toUnitVector());
            }
            case BACKWARD -> {
                if (validator.canMoveTo(position.subtract(orientation.toUnitVector())))
                    position = position.subtract(orientation.toUnitVector());
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }
}
