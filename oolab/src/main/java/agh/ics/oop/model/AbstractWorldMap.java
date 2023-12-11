package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    final private UUID mapId;
    final protected Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer visualizer;
    private final List<MapChangeListener> observers = new LinkedList<>();
    public AbstractWorldMap() {
        mapId = UUID.randomUUID();
        visualizer = new MapVisualizer(this);
    }
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (!canMoveTo(animal.position())) {
            throw new PositionAlreadyOccupiedException(animal.position());
        }
        animals.put(animal.position(), animal);
        mapChanged("Placed an animal at " + animal.position());
    }
    public void move(Animal animal, MoveDirection direction){
        Vector2d animalPosition = animal.position();
        animal.move(direction, this);
        if (!animal.isAt(animalPosition)) {
            animals.remove(animalPosition);
            animals.put(animal.position(), animal);
            mapChanged("Moved an animal to " + animal.position());
        } else if (direction == MoveDirection.TURN_LEFT || direction == MoveDirection.TURN_RIGHT) {
            mapChanged("Rotated an animal to " + animal);
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
    public void addObserver(MapChangeListener newObserver) { observers.add(newObserver); }
    public boolean removeObserver(MapChangeListener oldObserver) { return observers.remove(oldObserver); }
    public void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    public final UUID getId() { return mapId; }
    public String toString() {
        return visualizer.draw(getCurrentBounds());
    }
}
