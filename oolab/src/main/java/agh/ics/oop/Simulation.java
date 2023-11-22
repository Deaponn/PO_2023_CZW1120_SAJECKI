package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Simulation {
    private final List<Animal> animals = new LinkedList<>();
    private final List<MoveDirection> moves;
    private final WorldMap animalsMap;

    public Simulation(WorldMap animalsMap, List<Vector2d> positions, List<MoveDirection> moves) {
        this.animalsMap = animalsMap;
        this.moves = moves;
        for (int i = 0; i < positions.size(); i++) {
            Animal newAnimal = new Animal(positions.get(i));
            if (animalsMap.place(newAnimal)) {
                animals.add(newAnimal);
            }
        }
    }

    public void run() {
        run(true);
    }
    public void run(boolean printOutput) {
        ListIterator<Animal> animalsIterator = animals.listIterator();
        ListIterator<MoveDirection> movesIterator = moves.listIterator();
        while (movesIterator.hasNext()) {
            MoveDirection nextMove = movesIterator.next();
            if (!animalsIterator.hasNext()) {
                animalsIterator = animals.listIterator();
            }
            Animal nextAnimal = animalsIterator.next();
            animalsMap.move(nextAnimal, nextMove);
            if (printOutput) System.out.println(animalsMap);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
