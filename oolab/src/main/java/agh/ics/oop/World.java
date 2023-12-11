package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        // f b r l f f r r f f f f f f f f
        List<MoveDirection> directions;
        try {
            // testy SimulationEngine dla runSync i runAsync

//            directions = OptionsParser.parseMovement(args);
//            List<Vector2d> positions1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
//            AbstractWorldMap map1 = new GrassField(10);
//            map1.addObserver(new ConsoleMapDisplay());
//            List<Vector2d> positions2 = List.of(new Vector2d(2,3), new Vector2d(3,2), new Vector2d(1,2));
//            AbstractWorldMap map2 = new RectangularMap(10, 4);
//            List<Simulation> simulations = new LinkedList<>(List.of(new Simulation(map1, positions1, directions), new Simulation(map2, positions2, directions)));
//            SimulationEngine simulationEngine = new SimulationEngine(simulations);
//            simulationEngine.runSync();
//            simulationEngine.runAsync();

            // wykorzystanie runAsyncInThreadPool

            directions = OptionsParser.parseMovement(args);
            List<Simulation> simulations = new LinkedList<>();
            // wykonanie trwalo okolo:
            // 3.5s dla i < 1000
            // 15s dla i < 5_000
            // 27s dla i < 10_000
            for (int i = 0; i < 5_000; i++) {
                List<Vector2d> positions1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
                AbstractWorldMap map1 = new GrassField(10);
                map1.addObserver(new ConsoleMapDisplay());
                simulations.add(new Simulation(map1, positions1, directions));

                List<Vector2d> positions2 = List.of(new Vector2d(2,3), new Vector2d(3,2), new Vector2d(1,2));
                AbstractWorldMap map2 = new RectangularMap(10, 4);
                map2.addObserver(new ConsoleMapDisplay());
                simulations.add(new Simulation(map2, positions2, directions));
            }
            SimulationEngine simulationEngine = new SimulationEngine(simulations);
//            simulationEngine.runAsync();
            simulationEngine.runAsyncInThreadPool();
            simulationEngine.awaitSimulationsEnd();
        }
        catch (IllegalArgumentException exception) {
            System.out.println(exception);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("System zakonczyl dzialanie.");
    }
}