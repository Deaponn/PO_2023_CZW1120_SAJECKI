package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    final protected Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer visualizer;
    public AbstractWorldMap() {
        visualizer = new MapVisualizer(this);
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
        return !isOccupied(position);
    }
    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
    protected String internalToString(Vector2d start, Vector2d end) {
        return visualizer.draw(start, end);
    }
}
