package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        // f b r l f f r r f f f f f f f f
        List<MoveDirection> directions = OptionsParser.parseMovement(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run();
    }
}