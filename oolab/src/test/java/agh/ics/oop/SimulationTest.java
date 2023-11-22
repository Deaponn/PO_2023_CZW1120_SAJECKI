package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void run() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        Animal animal1 = new Animal(new Vector2d(3, 0));
        animal1.move(MoveDirection.TURN_LEFT);
        animal1.move(MoveDirection.TURN_LEFT);
        Animal animal2 = new Animal(new Vector2d(2, 4));
        assertTrue(simulation.getAnimals().equals(
                List.of(animal1, animal2)));
    }
}