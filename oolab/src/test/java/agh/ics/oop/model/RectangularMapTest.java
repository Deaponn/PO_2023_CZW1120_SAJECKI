package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

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
                new Vector2d(10,5),
                new Vector2d(9,5),
                new Vector2d(-2,-2000002),
                new Vector2d(2,-2000002)
        );
        List<Vector2d> desiredPositions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(9,5)
        );
        WorldMap map = new RectangularMap(10, 6);
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run(false);
        assertEquals(3, map.getElements().size());
        List<Vector2d> positionsList = map.getElements()
                .stream()
                .map(WorldElement::position)
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == desiredPositions.size()
                && positionsList.containsAll(desiredPositions) && desiredPositions.containsAll(positionsList));

        // test of values which are duplicated
        List<Vector2d> positionsDuplicates = List.of(
                new Vector2d(2,2),
                new Vector2d(2,2),
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(8,20),
                new Vector2d(8,20),
                new Vector2d(1,15),
                new Vector2d(8,20)
        );
        desiredPositions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(1,15),
                new Vector2d(8, 20)
        );
        map = new RectangularMap(20, 30);
        simulation = new Simulation(map, positionsDuplicates, directions);
        simulation.run(false);
        assertEquals(4, map.getElements().size());
        positionsList = map.getElements()
                .stream()
                .filter((WorldElement object) -> !Objects.equals(object.toString(), "*"))
                .map(WorldElement::position)
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == desiredPositions.size()
                && positionsList.containsAll(desiredPositions) && desiredPositions.containsAll(positionsList));
    }

    @Test
    void testPlaceException() throws PositionAlreadyOccupiedException {
        AbstractWorldMap map = new RectangularMap(7, 11);
        // should add
        map.place(new Animal(new Vector2d(3, 4)));
        // should add
        map.place(new Animal(new Vector2d(3, 9)));
        // should add
        map.place(new Animal(new Vector2d(6, 4)));
        // should throw
        assertThrows(PositionAlreadyOccupiedException.class,
                () -> map.place(new Animal(new Vector2d(3, 4))),
                "Expected to throw due to duplicate positions");

        RectangularMap map2 = new RectangularMap(4, 13);
        // should throw
        assertThrows(PositionAlreadyOccupiedException.class,
                () -> map2.place(new Animal(new Vector2d(9, 4))),
                "Expected to throw due to invalid position");
        // should throw
        assertThrows(PositionAlreadyOccupiedException.class,
                () -> map2.place(new Animal(new Vector2d(1, 14))),
                "Expected to throw due to invalid position");
        // should throw
        assertThrows(PositionAlreadyOccupiedException.class,
                () -> map2.place(new Animal(new Vector2d(-2, 4))),
                "Expected to throw due to invalid position");
        // should throw
        assertThrows(PositionAlreadyOccupiedException.class,
                () -> map2.place(new Animal(new Vector2d(1, -5))),
                "Expected to throw due to invalid position");
    }

    @Test
    void move() {
        // f b r l f f r r f f f f f f f f
        String[] args = new String[]{"f", "b", "l", "r", "r", "l", "f", "f", "f", "f", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> startPositions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(0,0),
                new Vector2d(0,1)
        );
        List<Vector2d> endPositions = List.of(
                new Vector2d(2, 0),
                new Vector2d(3, 5),
                new Vector2d(0, 0),
                new Vector2d(6, 1)
        );
        WorldMap map = new RectangularMap(8, 6);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        List<Vector2d> positionsList = map.getElements()
                .stream()
                .map(WorldElement::position)
                .toList();
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
                new Vector2d(2, 0),
                new Vector2d(3, 5)
        );
        WorldMap map = new RectangularMap(8, 6);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        // animals end positions
        assertTrue(map.isOccupied(endPositions.get(0)));
        assertTrue(map.isOccupied(endPositions.get(1)));
        // animal cant appear here
        assertFalse(map.isOccupied(new Vector2d(-1, -1)));
        assertFalse(map.isOccupied(new Vector2d(1, 3)));
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
                new Animal(new Vector2d(2, 0)),
                new Animal(new Vector2d(3, 7))
        );
        WorldMap map = new RectangularMap(8, 8);
        endAnimals.get(0).move(MoveDirection.TURN_RIGHT, map);
        endAnimals.get(0).move(MoveDirection.TURN_RIGHT, map);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        // animals end positions
        assertEquals(endAnimals.get(0), map.objectAt(endAnimals.get(0).position()));
        assertEquals(endAnimals.get(1), map.objectAt(endAnimals.get(1).position()));
        // animals cant appear here
        assertEquals(null, map.objectAt(new Vector2d(-1, -1)));
        assertEquals(null, map.objectAt(new Vector2d(2, 6)));
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
                new Vector2d(4, 0),
                new Vector2d(5, 5)
        );
        WorldMap map = new RectangularMap(18, 6);
        Simulation simulation = new Simulation(map, startPositions, directions);
        simulation.run(false);
        List<Vector2d> positionsList = map.getElements()
                .stream()
                .map((WorldElement object) -> object.position())
                .collect(Collectors.toList());
        assertTrue(positionsList.size() == endPositions.size()
                && positionsList.containsAll(endPositions) && endPositions.containsAll(positionsList));
    }
}