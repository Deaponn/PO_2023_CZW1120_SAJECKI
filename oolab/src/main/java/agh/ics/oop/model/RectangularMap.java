package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
public class RectangularMap implements WorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d mapStart = new Vector2d(0, 0);
    private final Vector2d mapEnd;
    public RectangularMap(int width, int height) {
        mapEnd = new Vector2d(width - 1, height - 1).add(mapStart);
    }

    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) {
            return false;
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }

    public void move(Animal animal, MoveDirection direction){
        Vector2d animalPosition = animal.getPosition();
        animal.move(direction, this);
        if (!animal.isAt(animalPosition)) {
            animals.remove(animalPosition);
            animals.put(animal.getPosition(), animal);
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return position.follows(mapStart) && position.precedes(mapEnd) && !isOccupied(position);
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(mapStart, mapEnd);
    }
}
