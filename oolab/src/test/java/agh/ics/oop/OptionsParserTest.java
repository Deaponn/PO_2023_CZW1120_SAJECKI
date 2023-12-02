package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseMovement() {
        // missing b, f, l, r
        String[] badArguments = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "c", "d", "e", "g", "h", "i", "j", "k", "m",
                "n", "o", "p", "q", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-",
                ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^",
                "_", "`", "{", "|", "}", "~", "word", "", "previous string is empty"
        };

        for (int i = 0; i < badArguments.length; i++) {
            int finalI = i;
            assertThrows(IllegalArgumentException.class,
                    () -> OptionsParser.parseMovement(new String[]{badArguments[finalI]}),
                    "Expected to throw due to invalid arguments");
        }

        List<MoveDirection> expectedOutput = new LinkedList<>(Arrays.asList(MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.TURN_LEFT, MoveDirection.TURN_RIGHT));

        String[] goodArguments = new String[]{"b", "f", "l", "r"};
        assertEquals(expectedOutput, OptionsParser.parseMovement(goodArguments));
    }
}