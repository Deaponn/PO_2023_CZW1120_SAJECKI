package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    final Map<Vector2d, Animal> animals = new HashMap<>();
    public AbstractWorldMap() {}

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

    public abstract boolean canMoveTo(Vector2d position);

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
}
