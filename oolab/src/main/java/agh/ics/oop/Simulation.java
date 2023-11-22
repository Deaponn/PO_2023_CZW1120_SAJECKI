package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Simulation {
    private final List<Animal> animals = new LinkedList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;
        for (int i = 0; i < positions.size(); i++) {
            animals.add(new Animal(positions.get(i)));
        }
    }

    public void run() {
        ListIterator<Animal> animalsIterator = animals.listIterator();
        ListIterator<MoveDirection> movesIterator = moves.listIterator();
        while (movesIterator.hasNext()) {
            MoveDirection nextMove = movesIterator.next();
            if (!animalsIterator.hasNext()) {
                animalsIterator = animals.listIterator();
            }
            Animal nextAnimal = animalsIterator.next();
            nextAnimal.move(nextMove);
            System.out.println("Zwierzę " + (animalsIterator.nextIndex() - 1) + " " + nextAnimal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
