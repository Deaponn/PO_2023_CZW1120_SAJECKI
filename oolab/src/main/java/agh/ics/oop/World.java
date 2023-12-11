package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        // f b r l f f r r f f f f f f f f
        List<MoveDirection> directions;
        try {
            directions = OptionsParser.parseMovement(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            AbstractWorldMap map = new GrassField(10);
            map.addObserver(new ConsoleMapDisplay());
            Simulation simulation = new Simulation(map, positions, directions);
            simulation.run();
        }
        catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }
}