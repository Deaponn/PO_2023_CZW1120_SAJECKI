package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final int MAP_BOUNDARY_NORTH = 4;
    private final int MAP_BOUNDARY_EAST = 4;
    private final int MAP_BOUNDARY_SOUTH = 0;
    private final int MAP_BOUNDARY_WEST = 0;

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public Animal() {
        this(new Vector2d(2, 2));
    }

    @Override
    public String toString() {
        return "Animal position: " + position.toString() + ",\torientation: " + orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return Objects.equals(position, this.position);
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case TURN_RIGHT -> orientation = orientation.next();
            case TURN_LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                switch(orientation) {
                    case NORTH -> {
                        if (position.getY() < MAP_BOUNDARY_NORTH) position = position.add(new Vector2d(0, 1));
                    }
                    case EAST -> {
                        if (position.getX() < MAP_BOUNDARY_EAST) position = position.add(new Vector2d(1, 0));
                    }
                    case SOUTH -> {
                        if (position.getY() > MAP_BOUNDARY_SOUTH) position = position.add(new Vector2d(0, -1));
                    }
                    case WEST -> {
                        if (position.getX() > MAP_BOUNDARY_WEST) position = position.add(new Vector2d(-1, 0));
                    }
                }
            }
            case BACKWARD -> {
                switch(orientation) {
                    case NORTH -> {
                        if (position.getY() > MAP_BOUNDARY_SOUTH) position = position.add(new Vector2d(0, -1));
                    }
                    case EAST -> {
                        if (position.getX() > MAP_BOUNDARY_WEST) position = position.add(new Vector2d(-1, 0));
                    }
                    case SOUTH -> {
                        if (position.getY() < MAP_BOUNDARY_NORTH) position = position.add(new Vector2d(0, 1));
                    }
                    case WEST -> {
                        if (position.getX() < MAP_BOUNDARY_EAST) position = position.add(new Vector2d(1, 0));
                    }
                }
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
