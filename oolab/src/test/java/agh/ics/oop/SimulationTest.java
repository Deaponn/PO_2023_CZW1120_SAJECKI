package agh.ics.oop;

import agh.ics.oop.model.*;
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
        WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run();
        assertEquals(" y\\x  0 1 2 3 4\n" +
                "  5: -----------\n" +
                "  4: | | | |^| |\n" +
                "  3: | | | | | |\n" +
                "  2: | | | | | |\n" +
                "  1: | | | | | |\n" +
                "  0: | | |v| | |\n" +
                " -1: -----------\n", map.toString());

        args = new String[]{"r", "l", "f", "b", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        directions = OptionsParser.parseMovement(args);
        //                                          should not add
        positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(1, 2));
        map = new RectangularMap(7, 3);
        simulation = new Simulation(map, positions, directions);
        simulation.run();
        assertEquals(" y\\x  0 1 2 3 4 5 6\n" +
                "  3: ---------------\n" +
                "  2: | |^| | | | | |\n" +
                "  1: | | | | | | | |\n" +
                "  0: | | | | |v| | |\n" +
                " -1: ---------------\n", map.toString());

        args = new String[]{"r", "l", "f", "b", "f", "f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f"};
        directions = OptionsParser.parseMovement(args);
        positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(1, 2));
        map = new RectangularMap(7, 6);
        simulation = new Simulation(map, positions, directions);
        simulation.run();
        assertEquals(" y\\x  0 1 2 3 4 5 6\n" +
                "  6: ---------------\n" +
                "  5: | | | | | | | |\n" +
                "  4: | |>|<| | | | |\n" +
                "  3: | | | | | | | |\n" +
                "  2: | | | | | |>| |\n" +
                "  1: | | | | | | | |\n" +
                "  0: | | | | | | | |\n" +
                " -1: ---------------\n", map.toString());
    }
}