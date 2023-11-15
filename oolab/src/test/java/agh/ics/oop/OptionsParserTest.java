package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseMovement() {
        String[] arguments = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-",
                ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^",
                "_", "`", "{", "|", "}", "~"
        };

        MoveDirection[] expectedOutput = {MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.TURN_LEFT, MoveDirection.TURN_RIGHT};

        assertTrue(Arrays.equals(expectedOutput, OptionsParser.parseMovement(arguments)));
    }
}