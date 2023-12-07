package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    final protected Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer visualizer;
    public AbstractWorldMap() {
        visualizer = new MapVisualizer(this);
    }
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (!canMoveTo(animal.position())) {
            throw new PositionAlreadyOccupiedException(animal.position());
        }
        animals.put(animal.position(), animal);
    }
    public void move(Animal animal, MoveDirection direction){
        Vector2d animalPosition = animal.position();
        animal.move(direction, this);
        if (!animal.isAt(animalPosition)) {
            animals.remove(animalPosition);
            animals.put(animal.position(), animal);
        }
    }
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
    public Collection<WorldElement> getElements() {
        return new HashSet<>(animals.values());
    }
    public String toString() {
        return visualizer.draw(getCurrentBounds());
    }
}
