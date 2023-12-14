package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Simulation implements Runnable {
    private final List<Animal> animals = new LinkedList<>();
    private final List<MoveDirection> moves;
    private final WorldMap worldMap;
    public Simulation(WorldMap worldMap, List<Vector2d> positions, List<MoveDirection> moves) {
        this.worldMap = worldMap;
        this.moves = moves;
        for (Vector2d position : positions) {
            Animal newAnimal = new Animal(position);
            try {
                worldMap.place(newAnimal);
                animals.add(newAnimal);
            } catch (PositionAlreadyOccupiedException exception) {
                System.out.println(exception);
            }
        }
    }

    @Override
    public void run() {
        ListIterator<Animal> animalsIterator = animals.listIterator();
        for (MoveDirection nextMove : moves) {
            if (!animalsIterator.hasNext()) {
                animalsIterator = animals.listIterator();
            }
            Animal nextAnimal = animalsIterator.next();
            worldMap.move(nextAnimal, nextMove);
        }
    }
    public List<Animal> getAnimals() {
        return animals;
    }
}
