package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {

    public void start(Stage primaryStage) {
        String[] args = {"r", "l", "f", "f", "f", "r", "r", "r", "b", "f", "l", "b", "f", "f", "f"};
//        String[] args = getParameters().getRaw().toArray(new String[0]);
        List<MoveDirection> moves = OptionsParser.parseMovement(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(map, positions, moves);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();
            presenter.setWorldMap(map);
            map.addObserver(presenter);
            configureStage(primaryStage, viewRoot);
            primaryStage.show();
            simulation.run();
            System.out.println("Symulacja wywołała się pomyślnie");
        } catch (IOException e) {
            System.out.println("Symulacja nie wywołała się");
            e.printStackTrace();
        }
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
