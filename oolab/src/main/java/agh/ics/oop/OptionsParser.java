package main.java.agh.ics.oop;

import main.java.agh.ics.oop.model.*;

public class OptionsParser {
    public static MoveDirection[] parseMovement(String[] options) {
        MoveDirection[] temp = new MoveDirection[options.length];
        int currentIdx = 0;
        for (String option : options) {
            switch (option) {
                case "f" -> temp[currentIdx] = MoveDirection.FORWARD;
                case "b" -> temp[currentIdx] = MoveDirection.BACKWARD;
                case "l" -> temp[currentIdx] = MoveDirection.TURN_LEFT;
                case "r" -> temp[currentIdx] = MoveDirection.TURN_RIGHT;
            }
            if (temp[currentIdx] != null) currentIdx++;
        }
        MoveDirection[] output = new MoveDirection[currentIdx];
        System.arraycopy(temp, 0, output, 0, output.length);
        return output;
    }
}