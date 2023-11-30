package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void place() {
        // f b r l f f r r f f f f f f f f

        // test of simple placement
        String[] args = {};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(8,20),
                new Vector2d(-2,-2000002)
        );
        WorldMap map = new GrassField(10);
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run(false);
        assertEquals(14, map.getElements().size());
        List<Vector2d> positionsList = map.getElements()
                .stream()
                .filter((WorldElement object) -> object.toString() != "*")
                .map((WorldElement object) -> object.position())
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == positions.size()
                && positionsList.containsAll(positions) && positions.containsAll(positionsList));

        // test of values which are duplicated
        List<Vector2d> positionsDuplicates = List.of(
                new Vector2d(2,2),
                new Vector2d(2,2),
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(8,20),
                new Vector2d(8,20),
                new Vector2d(8,20),
                new Vector2d(-2,-2000002)
                );
        map = new GrassField(20);
        simulation = new Simulation(map, positionsDuplicates, directions);
        simulation.run(false);
        assertEquals(24, map.getElements().size());
        positionsList = map.getElements()
                .stream()
                .filter((WorldElement object) -> !Objects.equals(object.toString(), "*"))
                .map(WorldElement::position)
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == positions.size()
                && positionsList.containsAll(positions) && positions.containsAll(positionsList));
    }

    @Test
    void move() {
        // f b r l f f r r f f f f f f f f
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> startPositions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4)
        );
        List<Vector2d> endPositions = List.of(
                new Vector2d(2, -1),
                new Vector2d(3, 7)
        );
        WorldMap map = new GrassField(8);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        List<Vector2d> positionsList = map.getElements()
                .stream()
                .filter((WorldElement object) -> object.toString() != "*")
                .map((WorldElement object) -> object.position())
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == endPositions.size()
                && positionsList.containsAll(endPositions) && endPositions.containsAll(positionsList));
    }

    @Test
    void isOccupied() {
        // f b r l f f r r f f f f f f f f
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> startPositions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4)
        );
        List<Vector2d> endPositions = List.of(
                new Vector2d(2, -1),
                new Vector2d(3, 7)
        );
        WorldMap map = new GrassField(8);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        Vector2d firstBush = map.getElements()
                .stream()
                .filter((WorldElement object) -> object.toString() == "*")
                .map((WorldElement object) -> object.position())
                .collect(Collectors.toList())
                .get(0);
        // animals end positions
        assertTrue(map.isOccupied(endPositions.get(0)));
        assertTrue(map.isOccupied(endPositions.get(1)));
        // bush cant appear here
        assertFalse(map.isOccupied(new Vector2d(-1, -1)));
        // get first bush position and check it for being occupied
        assertTrue(map.isOccupied(firstBush));
    }

    @Test
    void objectAt() {
        // f b r l f f r r f f f f f f f f
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> startPositions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4)
        );
        List<Animal> endAnimals = List.of(
                new Animal(new Vector2d(2, -1)),
                new Animal(new Vector2d(3, 7))
        );
        WorldMap map = new GrassField(8);
        endAnimals.get(0).move(MoveDirection.TURN_RIGHT, map);
        endAnimals.get(0).move(MoveDirection.TURN_RIGHT, map);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        Vector2d firstBush = map.getElements()
                .stream()
                .filter((WorldElement object) -> object.toString() == "*")
                .map((WorldElement object) -> object.position())
                .collect(Collectors.toList())
                .get(0);
        // animals end positions
        assertEquals(endAnimals.get(0), map.objectAt(endAnimals.get(0).position()));
        assertEquals(endAnimals.get(1), map.objectAt(endAnimals.get(1).position()));
        // bush cant appear here
        assertEquals(null, map.objectAt(new Vector2d(-1, -1)));
        // get first bush position and check if it matches the object in the map
        assertEquals(new Grass(firstBush), map.objectAt(firstBush));
    }

    @Test
    void getElements() {
        // f b r l f f r r f f f f f f f f
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> startPositions = List.of(
                new Vector2d(4,2),
                new Vector2d(5,4)
        );
        List<Vector2d> endPositions = List.of(
                new Vector2d(4, -1),
                new Vector2d(5, 7)
        );
        WorldMap map = new GrassField(18);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        List<Vector2d> positionsList = map.getElements()
                .stream()
                .filter((WorldElement object) -> object.toString() != "*")
                .map((WorldElement object) -> object.position())
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == endPositions.size()
                && positionsList.containsAll(endPositions) && endPositions.containsAll(positionsList));
    }
}