package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parseMovement(String[] options) {
        List<MoveDirection> output = new LinkedList<>();
        for (String option : options) {
            switch (option) {
                case "f" -> output.add(MoveDirection.FORWARD);
                case "b" -> output.add(MoveDirection.BACKWARD);
                case "l" -> output.add(MoveDirection.TURN_LEFT);
                case "r" -> output.add(MoveDirection.TURN_RIGHT);
                default -> throw new IllegalArgumentException("Argument is not a legal move: " + option);
            }
        }
        return output;
    }
}