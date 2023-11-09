package main.java.agh.ics.oop;

import main.java.agh.ics.oop.model.*;

public class OptionsParser {
    public static MoveDirection[] parseMovement(String[] options) {
        MoveDirection output[] = new MoveDirection[options.length];
        int currentIdx = 0;
        // l l f f f r b k m l s b f
        for(int i = 0; i < options.length; i++) {
            switch(options[i]) {
                case "f" -> output[currentIdx] = MoveDirection.FORWARD;
                case "b" -> output[currentIdx] = MoveDirection.BACKWARD;
                case "l" -> output[currentIdx] = MoveDirection.TURN_LEFT;
                case "r" -> output[currentIdx] = MoveDirection.TURN_RIGHT;
            }
            if (output[currentIdx] != null) currentIdx++;
        }
        return output;
    }
}